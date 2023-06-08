package ci.itech.oedatauploader.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ci.itech.oedatauploader.entities.VlAnalysisRecord;
import ci.itech.oedatauploader.service.VlAnalysisRecordService;
import ci.itech.oedatauploader.utils.OEHttpClient;

@Component
@EnableScheduling
public class Task {

    @Autowired
    private VlAnalysisRecordService vlService;

    Logger logger = LoggerFactory.getLogger(Task.class);

    @Value("${platform.name}")
    private String lab;

    @Value("${upload.uri}")
    private String uriPath;
    @Value("${upload.username}")
    private String username;
    @Value("${upload.password}")
    private String password;

    @Scheduled(fixedDelay = 5000)
    private void readVlAnalysisForInsert(){
        while(true){
            List<VlAnalysisRecord> analysis = vlService.getAnalysisToInsert();
            if(analysis.size() == 0){
                break;
            }
            List<VlAnalysisRecord> createdAnalysis = vlService.createOrUpdateAll(analysis);
            if(createdAnalysis.size()>0){
                vlService.updateReadAnalysis(createdAnalysis);
            }
        }
    }

    @Scheduled(fixedDelay = 5000)
    private void readVlAnalysisForUpdate(){
        while(true){
            try{
                List<VlAnalysisRecord> analysis = vlService.getAnalysisToUpdate();
                if(analysis.size() == 0){
                    break;
                }
                List<String> labnos = analysis.stream().map(e->e.getLabno()).collect(Collectors.toList());
                List<VlAnalysisRecord> oldAnalysis = vlService.findByLabnos(labnos);
                HashMap<String,VlAnalysisRecord> mapOldAnalysis = new HashMap<>();
                oldAnalysis.forEach(el->{
                    mapOldAnalysis.put(el.getLabno(), el);
                });
                for(int i =0;i<analysis.size();i++){
                    BeanUtils.copyProperties(mapOldAnalysis.get(analysis.get(i).getLabno()),analysis.get(i),"id");
                }
                List<VlAnalysisRecord> createdAnalysis = vlService.createOrUpdateAll(analysis);
                if(createdAnalysis.size()>0){
                    vlService.updateReadAnalysis(createdAnalysis);
                }
            }catch(Exception ex){
                logger.error("Task - readVlAnalysisForUpdate", ex);
                break;
            }
        }
    }

    @Scheduled(fixedDelay = 5000)
    private void uploader(){
        boolean isInsertTaskOver = false;
        boolean isUpdateTaskOver = false;

        //Do insert
        while(!isInsertTaskOver){
            List<VlAnalysisRecord> dataToPushInsert = vlService.getAnalysisToPush(VlAnalysisRecord.STATUS_TO_INSERT);
            if(dataToPushInsert.size()>0){
                HttpResponse response =  OEHttpClient.sendData(uriPath, username,password, dataToPushInsert);
                if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
                    vlService.updatePushedAnalysis(dataToPushInsert);
                }
            }
            else{
                isInsertTaskOver = true;
            }
            break;
        }  
        
        //Do update
        while(!isUpdateTaskOver){
            List<VlAnalysisRecord> dataToPushUpdate = vlService.getAnalysisToPush(VlAnalysisRecord.STATUS_TO_UPDATE);
            if(dataToPushUpdate.size()>0){
                try{
                    HttpResponse response =  OEHttpClient.sendData(uriPath,username,password,dataToPushUpdate);
                    System.out.println(response.getStatusLine());
                    if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
                        vlService.updatePushedAnalysis(dataToPushUpdate);
                    }
                }
                catch(Exception ex){
                    logger.error("Task - uploader()", ex);
                    isUpdateTaskOver = true;
                }
            }
            else{
                isUpdateTaskOver = true;
            }
            break;
        }

    }
    
}
