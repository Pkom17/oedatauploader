package ci.itech.oedatauploader.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ci.itech.oedatauploader.entities.VlAnalysisRecord;
import ci.itech.oedatauploader.repositories.VlAnalysisRecordRepo;
import ci.itech.oedatauploader.service.VlAnalysisRecordService;

@Service
@Transactional
public class VlAnalysisRecordserviceImpl implements VlAnalysisRecordService{

    Logger logger = LoggerFactory.getLogger(VlAnalysisRecordserviceImpl.class);

    @Value("${platform.name}")
    private String platformName;

    @Autowired
    private VlAnalysisRecordRepo repo;

    @PersistenceContext
	private EntityManager em;

    @Override
    public VlAnalysisRecord create(VlAnalysisRecord record) {
        try{
            return repo.save(record);
        }catch(Exception ex){
            logger.error(null, ex);
            return null;
        }
    }

    @Override
    public VlAnalysisRecord update(VlAnalysisRecord record) {
        return this.create(record);
    }

    @Override
    public List<VlAnalysisRecord> createOrUpdateAll(List<VlAnalysisRecord> recordList) {
        try{
            return repo.saveAll(recordList);
        }catch(Exception ex){
            logger.error(null, ex);
            return null;
        }
    }

    @Override
    public VlAnalysisRecord getOne(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOne'");
    }

    @Override
    public List<VlAnalysisRecord> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public int getTotal() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTotal'");
    }

    @Override
    public int getAnalysisToInsertTotal() {
        List<Integer> results = new ArrayList<>();
        String sql = "SELECT count(*) FROM vl_analysis_view WHERE upload_flag = 'TO_READ'";
		try {
			Query query = em.createNativeQuery(sql,Integer.class);
			results = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results.get(0);
    }

    @Override
    public int getAnalysisToUpdateTotal() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getToUpdateAnalysisTotal'");
    }

    @Override
    public boolean delete(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public VlAnalysisRecord findByLabno(String labno) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByLabno'");
    }

    @Override
    public List<VlAnalysisRecord> findByLabnos(List<String> labnos) {
        return repo.findAllByLabnoIn(labnos);
    }

    @Override
    public VlAnalysisRecord findBySampleId(String sampleId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findBySampleId'");
    }

    @Override
    public List<VlAnalysisRecord> getAnalysisToInsert() {
        List<VlAnalysisRecord> response = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sql = "SELECT * FROM vl_analysis_view WHERE upload_flag = 'TO_READ' LIMIT 100 ";
		try {
			Query query = em.createNativeQuery(sql);
            List<Object[]> results = query.getResultList();
            for (Object[] o : results) {
                if(ObjectUtils.isEmpty(o[1])){ // skip if labno is empty
                    continue;
                }
                if(ObjectUtils.isEmpty(o[7]) && ObjectUtils.isEmpty(o[6])){ // skip if external_id and national_id are empty
                    continue;
                }
                VlAnalysisRecord analysis = new VlAnalysisRecord();
                analysis.setPlatform(platformName);
                analysis.setStudyname("VL");
                analysis.setSampleId(Integer.valueOf(o[0].toString()));
                analysis.setLabno(ObjectUtils.isNotEmpty(o[1])?o[1].toString():null);
                analysis.setEntryDate(ObjectUtils.isNotEmpty(o[2])?sdf.parse(o[2].toString()):null);
                analysis.setReceptionDate(ObjectUtils.isNotEmpty(o[3])?sdf.parse(o[3].toString()):null);
                analysis.setCollectionDate(ObjectUtils.isNotEmpty(o[3])?sdf.parse(o[4].toString()):null);
                analysis.setStatus(ObjectUtils.isNotEmpty(o[5])?o[5].toString():null);
                String sampleStatus = ObjectUtils.isNotEmpty(o[5])?o[5].toString():null;
                String analysisStatus = ObjectUtils.isNotEmpty(o[16])?o[16].toString():null;
                analysis.setStatus(getStatus(analysisStatus,sampleStatus));
                analysis.setPatientsubjectNumber(ObjectUtils.isNotEmpty(o[6])?o[6].toString():null);
                analysis.setPatientSiteSubjectNumber(ObjectUtils.isNotEmpty(o[7])?o[7].toString():null);
                analysis.setPatientCode(ObjectUtils.isNotEmpty(o[7])?o[7].toString():o[6].toString());
                analysis.setBirthDate(sdf.parse(o[8].toString()));
                analysis.setGender(o[9].toString());
                analysis.setSiteCode(ObjectUtils.isNotEmpty(o[10])?o[10].toString():null);
                analysis.setSiteName(o[11].toString());
                analysis.setSiteDatimCode(ObjectUtils.isNotEmpty(o[12])?o[12].toString():null);
                analysis.setSiteDatimName(ObjectUtils.isNotEmpty(o[13])?o[13].toString():null);
                analysis.setCompletedDate(ObjectUtils.isNotEmpty(o[14])?sdf.parse(o[14].toString()):null);
                analysis.setReleasedDate(ObjectUtils.isNotEmpty(o[15])?sdf.parse(o[15].toString()):null);
                analysis.setTestResult(ObjectUtils.isNotEmpty(o[17])?o[17].toString():null);
                analysis.setSpecimenType(o[18].toString());
                analysis.setHivStatus(ObjectUtils.isNotEmpty(o[25])?o[25].toString():null);
                analysis.setOrderReason(ObjectUtils.isNotEmpty(o[26])?o[26].toString():null);
                Boolean pregnancy = null;
                if(ObjectUtils.isNotEmpty(o[27])){
                    if(o[27].toString().equals("1251")){
                        pregnancy= false;
                    }
                    else if(o[27].toString().equals("1253")){
                        pregnancy = true;
                    }
                }
                Boolean vlSuckle = null;
                if(ObjectUtils.isNotEmpty(o[28])){
                    if(o[28].toString().equals("1251")){
                        pregnancy= false;
                    }
                    else if(o[28].toString().equals("1253")){
                        vlSuckle = true;
                    }
                }
                analysis.setVlPregnancy(pregnancy);
                analysis.setVlSuckle(vlSuckle);
                String reg1 = ObjectUtils.isNotEmpty(o[34])?o[34].toString():"";
                String reg2 = ObjectUtils.isNotEmpty(o[33])?o[33].toString():"";
                String reg3 = ObjectUtils.isNotEmpty(o[32])?o[32].toString():"";
                String reg4 = ObjectUtils.isNotEmpty(o[31])?o[31].toString():"";
                String regimen = reg1+" "+reg2+" "+reg3+" "+reg4;
                analysis.setRegimen(regimen.trim());
                analysis.setAnalysisId(Integer.valueOf(o[38].toString()));
                analysis.setRecordStatus(VlAnalysisRecord.STATUS_TO_INSERT);
                response.add(analysis);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
    }

    private String getStatus(String analysisStatus, String sampleStatus) {
        String status = "0";
       if((analysisStatus.equals("6")) && (sampleStatus.equals("14"))){
           return VlAnalysisRecord.ANALYSIS_FAILED;
       }else 
       if((analysisStatus.equals("7")) && (sampleStatus.equals("2") || sampleStatus.equals("3"))){
            return VlAnalysisRecord.ANALYSIS_FAILED;
        }else if(analysisStatus.equals("15") && sampleStatus.equals("2")){
            return VlAnalysisRecord.ANALYSIS_COMPLETED;
        } else if(analysisStatus.equals("7") && sampleStatus.equals("2")){
            return VlAnalysisRecord.ANALYSIS_REJECTED;
        } else if(analysisStatus.equals("4") && sampleStatus.equals("1")){
            return VlAnalysisRecord.ANALYSIS_CREATED;
        }else if(analysisStatus.equals("13") && sampleStatus.equals("12")){
            return VlAnalysisRecord.ANALYSIS_NON_CONFORM;
        }
        else if(analysisStatus.equals("6") && sampleStatus.equals("3")){
            return VlAnalysisRecord.ANALYSIS_RELEASED;
        }
       return status;
    }

    @Override
    public void updateReadAnalysis(List<VlAnalysisRecord> recordList){
        String sql = "UPDATE analysis SET upload_flag = 'READ' WHERE id IN :ids";
        List<Integer> listId = recordList.stream().map(e->e.getAnalysisId()).collect(Collectors.toList());
        try {
			Query query = em.createNativeQuery(sql);
			query.setParameter("ids", listId);
            query.executeUpdate();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void updatePushedAnalysis(List<VlAnalysisRecord> recordList){
        String sql = "UPDATE vl_analysis_record SET record_status = :status WHERE id IN :ids";
        List<Integer> listId = recordList.stream().map(e->e.getId()).collect(Collectors.toList());
        try {
			Query query = em.createNativeQuery(sql);
			query.setParameter("ids", listId);
			query.setParameter("status", VlAnalysisRecord.STATUS_UP_TO_DATE);
            query.executeUpdate();
        }catch(Exception ex){
            ex.printStackTrace(); 
        }
    }


    @Override
    public List<VlAnalysisRecord> getAnalysisToUpdate() {
        List<VlAnalysisRecord> response = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sql = "SELECT * FROM vl_analysis_view WHERE upload_flag = 'TO_UPDATE' LIMIT 25 ";
		try {
			Query query = em.createNativeQuery(sql);
			List<Object[]> results = query.getResultList();
            for (Object[] o : results) {
                if(ObjectUtils.isEmpty(o[1])){ // skip if labno is empty
                    continue;
                }
                if(ObjectUtils.isEmpty(o[7]) && ObjectUtils.isEmpty(o[6])){ // skip if external_id and national_id are empty
                    continue;
                }
                VlAnalysisRecord analysis = new VlAnalysisRecord();
                analysis.setPlatform(platformName);
                analysis.setStudyname("VL");
                analysis.setSampleId(Integer.valueOf(o[0].toString()));
                analysis.setLabno(ObjectUtils.isNotEmpty(o[1])?o[1].toString():null);
                analysis.setEntryDate(ObjectUtils.isNotEmpty(o[2])?sdf.parse(o[2].toString()):null);
                analysis.setReceptionDate(ObjectUtils.isNotEmpty(o[3])?sdf.parse(o[3].toString()):null);
                analysis.setCollectionDate(ObjectUtils.isNotEmpty(o[3])?sdf.parse(o[4].toString()):null);
                analysis.setStatus(ObjectUtils.isNotEmpty(o[5])?o[5].toString():null);
                String sampleStatus = ObjectUtils.isNotEmpty(o[5])?o[5].toString():null;
                String analysisStatus = ObjectUtils.isNotEmpty(o[16])?o[16].toString():null;
                analysis.setStatus(getStatus(analysisStatus,sampleStatus));
                analysis.setPatientsubjectNumber(ObjectUtils.isNotEmpty(o[6])?o[6].toString():null);
                analysis.setPatientSiteSubjectNumber(ObjectUtils.isNotEmpty(o[7])?o[7].toString():null);
                analysis.setPatientCode(ObjectUtils.isNotEmpty(o[7])?o[7].toString():o[6].toString());
                analysis.setBirthDate(sdf.parse(o[8].toString()));
                analysis.setGender(o[9].toString());
                analysis.setSiteCode(ObjectUtils.isNotEmpty(o[10])?o[10].toString():null);
                analysis.setSiteName(o[11].toString());
                analysis.setSiteDatimCode(ObjectUtils.isNotEmpty(o[12])?o[12].toString():null);
                analysis.setSiteDatimName(ObjectUtils.isNotEmpty(o[13])?o[13].toString():null);
                analysis.setCompletedDate(ObjectUtils.isNotEmpty(o[14])?sdf.parse(o[14].toString()):null);
                analysis.setReleasedDate(ObjectUtils.isNotEmpty(o[15])?sdf.parse(o[15].toString()):null);
                analysis.setTestResult(ObjectUtils.isNotEmpty(o[17])?o[17].toString():null);
                analysis.setSpecimenType(o[18].toString());
                analysis.setHivStatus(ObjectUtils.isNotEmpty(o[25])?o[25].toString():null);
                analysis.setOrderReason(ObjectUtils.isNotEmpty(o[26])?o[26].toString():null);
                Boolean pregnancy = null;
                if(ObjectUtils.isNotEmpty(o[27])){
                    if(o[27].toString().equals("1251")){
                        pregnancy= false;
                    }
                    else if(o[27].toString().equals("1253")){
                        pregnancy = true;
                    }
                }
                Boolean vlSuckle = null;
                if(ObjectUtils.isNotEmpty(o[28])){
                    if(o[28].toString().equals("1251")){
                        pregnancy= false;
                    }
                    else if(o[28].toString().equals("1253")){
                        vlSuckle = true;
                    }
                }
                analysis.setVlPregnancy(pregnancy);
                analysis.setVlSuckle(vlSuckle);
                String reg1 = ObjectUtils.isNotEmpty(o[34])?o[34].toString():"";
                String reg2 = ObjectUtils.isNotEmpty(o[33])?o[33].toString():"";
                String reg3 = ObjectUtils.isNotEmpty(o[32])?o[32].toString():"";
                String reg4 = ObjectUtils.isNotEmpty(o[31])?o[31].toString():"";
                String regimen = reg1+" "+reg2+" "+reg3+" "+reg4;
                analysis.setRegimen(regimen.trim());
                analysis.setAnalysisId(Integer.valueOf(o[38].toString()));
                analysis.setRecordStatus(VlAnalysisRecord.STATUS_TO_UPDATE);
                response.add(analysis);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
        
    }

    @Override
    public List<VlAnalysisRecord> getAnalysisToPush(short status) {
        List<VlAnalysisRecord> response = new ArrayList<>();
        String sql = "SELECT * FROM vl_analysis_record WHERE record_status = :status LIMIT 25 ";
		try {
			Query query = em.createNativeQuery(sql,VlAnalysisRecord.class);
            query.setParameter("status", status);
			response = query.getResultList();
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
    }
    
}
