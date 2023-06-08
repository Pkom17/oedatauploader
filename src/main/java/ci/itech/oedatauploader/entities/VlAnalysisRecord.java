package ci.itech.oedatauploader.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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

}
