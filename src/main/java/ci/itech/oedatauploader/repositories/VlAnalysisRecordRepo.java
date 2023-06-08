package ci.itech.oedatauploader.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ci.itech.oedatauploader.entities.VlAnalysisRecord;

public interface VlAnalysisRecordRepo extends JpaRepository<VlAnalysisRecord, Integer>, JpaSpecificationExecutor<VlAnalysisRecord>{

    public List<VlAnalysisRecord> findAllByLabnoIn(List<String> labnos);

}