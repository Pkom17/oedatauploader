package ci.itech.oedatauploader.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "vl_analysis_record")
public class VlAnalysisRecord implements Serializable{
    private static final long serialVersionUID = 1L;
    public static short STATUS_UP_TO_DATE = 3;
	public static short  STATUS_TO_INSERT= 1;
	public static short STATUS_TO_UPDATE = 2;
    public static String ANALYSIS_CREATED = "1";
	public static String  ANALYSIS_COMPLETED= "2";
	public static String ANALYSIS_RELEASED = "3";
    public static String ANALYSIS_FAILED = "4";
    public static String ANALYSIS_NON_CONFORM = "5";
    public static String ANALYSIS_REJECTED = "6";

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    @Column(name = "platform", nullable = false)
    private String platform;
    @Column(name = "sample_id", nullable = false)
    private Integer sampleId;
    @Column(name = "analysis_id", nullable = false)
    private Integer analysisId;
    @Column(name = "labno", nullable = false)
    private String labno;
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "record_status", nullable = false)
    private short recordStatus;
    @Column(name = "patient_subject_number", nullable = true)
    private String patientsubjectNumber;
    @Column(name = "patient_site_subject_number", nullable = true)
    private String patientSiteSubjectNumber;
    @Column(name = "patient_code", nullable = false)
    private String patientCode;
    @Column(name = "gender", nullable = false)
    private String gender;
    @Column(name = "birth_date", nullable = false)
    private Date birthDate;
    @Column(name = "age_year", nullable = false)
    private int ageYear;
    @Column(name = "age_month", nullable = false)
    private int ageMonth;
    @Column(name = "hiv_status", nullable = true)
    private String hivStatus;
    @Column(name = "regimen", nullable = false)
    private String regimen;
    @Column(name = "study_name", nullable = false)
    private String studyname;
    @Column(name = "site_code", nullable = true)
    private String siteCode;
    @Column(name = "site_name", nullable = false)
    private String siteName;
    @Column(name = "site_datim_code", nullable = true)
    private String siteDatimCode;
    @Column(name = "site_datim_name", nullable = true)
    private String siteDatimName;
    @Column(name = "collection_date", nullable = false)
    private Date collectionDate;
    @Column(name = "reception_date", nullable = false)
    private Date entryDate;
    @Column(name = "entry_date", nullable = false)
    private Date receptionDate;
    @Column(name = "completed_date", nullable = true)
    private Date completedDate;
    @Column(name = "released_date", nullable = true)
    private Date releasedDate;
    @Column(name = "specimen_type", nullable = false)
    private String specimenType;
    @Column(name = "test_result", nullable = true)
    private String testResult;
    @Column(name = "test_result_int", nullable = true)
    private int testResultInt;
    @Column(name = "order_reason", nullable = true)
    private String orderReason;
    @Column(name = "vl_pregnancy", nullable = true)
    private Boolean vlPregnancy;
    @Column(name = "vl_suckle", nullable = true)
    private Boolean vlSuckle;
    @Column(name = "report_name", nullable = true)
    private String reportName;
    @Column(name = "created_at", nullable = true)
    private Date createdAt;
    @Column(name = "lastupdated_at", nullable = true)
    private Date lastupdateddAt;
    
    public VlAnalysisRecord() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Integer getSampleId() {
        return sampleId;
    }

    public void setSampleId(Integer sampleId) {
        this.sampleId = sampleId;
    }

    public Integer getAnalysisId() {
        return analysisId;
    }

    public void setAnalysisId(Integer analysisId) {
        this.analysisId = analysisId;
    }

    public String getLabno() {
        return labno;
    }

    public void setLabno(String labno) {
        this.labno = labno;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public short getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(short recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getPatientsubjectNumber() {
        return patientsubjectNumber;
    }

    public void setPatientsubjectNumber(String patientsubjectNumber) {
        this.patientsubjectNumber = patientsubjectNumber;
    }

    public String getPatientSiteSubjectNumber() {
        return patientSiteSubjectNumber;
    }

    public void setPatientSiteSubjectNumber(String patientSiteSubjectNumber) {
        this.patientSiteSubjectNumber = patientSiteSubjectNumber;
    }

    public String getPatientCode() {
        return patientCode;
    }

    public void setPatientCode(String patientCode) {
        this.patientCode = patientCode;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getAgeYear() {
        return ageYear;
    }

    public void setAgeYear(int ageYear) {
        this.ageYear = ageYear;
    }

    public int getAgeMonth() {
        return ageMonth;
    }

    public void setAgeMonth(int ageMonth) {
        this.ageMonth = ageMonth;
    }

    public String getHivStatus() {
        return hivStatus;
    }

    public void setHivStatus(String hivStatus) {
        this.hivStatus = hivStatus;
    }

    public String getRegimen() {
        return regimen;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    public String getStudyname() {
        return studyname;
    }

    public void setStudyname(String studyname) {
        this.studyname = studyname;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteDatimCode() {
        return siteDatimCode;
    }

    public void setSiteDatimCode(String siteDatimCode) {
        this.siteDatimCode = siteDatimCode;
    }

    public String getSiteDatimName() {
        return siteDatimName;
    }

    public void setSiteDatimName(String siteDatimName) {
        this.siteDatimName = siteDatimName;
    }

    public Date getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(Date collectionDate) {
        this.collectionDate = collectionDate;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Date getReceptionDate() {
        return receptionDate;
    }

    public void setReceptionDate(Date receptionDate) {
        this.receptionDate = receptionDate;
    }

    public Date getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
    }

    public Date getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(Date releasedDate) {
        this.releasedDate = releasedDate;
    }

    public String getSpecimenType() {
        return specimenType;
    }

    public void setSpecimenType(String specimenType) {
        this.specimenType = specimenType;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public int getTestResultInt() {
        return testResultInt;
    }

    public void setTestResultInt(int testResultInt) {
        this.testResultInt = testResultInt;
    }

    public String getOrderReason() {
        return orderReason;
    }

    public void setOrderReason(String orderReason) {
        this.orderReason = orderReason;
    }

    public Boolean getVlPregnancy() {
        return vlPregnancy;
    }

    public void setVlPregnancy(Boolean vlPregnancy) {
        this.vlPregnancy = vlPregnancy;
    }

    public Boolean getVlSuckle() {
        return vlSuckle;
    }

    public void setVlSuckle(Boolean vlSuckle) {
        this.vlSuckle = vlSuckle;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLastupdateddAt() {
        return lastupdateddAt;
    }

    public void setLastupdateddAt(Date lastupdateddAt) {
        this.lastupdateddAt = lastupdateddAt;
    }

    
}
