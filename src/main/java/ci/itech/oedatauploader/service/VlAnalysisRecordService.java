package ci.itech.oedatauploader.service;

import java.util.List;

import ci.itech.oedatauploader.entities.VlAnalysisRecord;

public interface VlAnalysisRecordService {

    VlAnalysisRecord create(VlAnalysisRecord record);

	VlAnalysisRecord update(VlAnalysisRecord record);

    List<VlAnalysisRecord> createOrUpdateAll(List<VlAnalysisRecord> recordList);

	void updateReadAnalysis(List<VlAnalysisRecord> recordList);

    void updatePushedAnalysis(List<VlAnalysisRecord> recordList);

	VlAnalysisRecord getOne(Integer id);

	List<VlAnalysisRecord> getAll();

	int getTotal();

    int getAnalysisToInsertTotal();

    int getAnalysisToUpdateTotal();

	boolean delete(Integer id);

	VlAnalysisRecord findByLabno(String labno);

    List<VlAnalysisRecord> findByLabnos(List<String> labnos);

	VlAnalysisRecord findBySampleId(String sampleId);

    List<VlAnalysisRecord> getAnalysisToInsert();

    List<VlAnalysisRecord> getAnalysisToUpdate();

    List<VlAnalysisRecord> getAnalysisToPush(short status); 

}
