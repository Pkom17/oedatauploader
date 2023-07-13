package ci.itech.oedatauploader.repositories;

import ci.itech.oedatauploader.entities.VlAnalysisRecord;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;



public interface VlAnalysisRecordRepo extends JpaRepository<VlAnalysisRecord, Integer>, JpaSpecificationExecutor<VlAnalysisRecord>{

    public List<VlAnalysisRecord> findAllByLabnoIn(List<String> labnos);

}