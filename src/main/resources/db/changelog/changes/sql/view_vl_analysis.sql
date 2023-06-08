CREATE OR REPLACE VIEW vl_analysis_view AS (
        SELECT DISTINCT s.id as sample_id, s.accession_number, s.entered_date, s.received_date, s.collection_date, s.status_id 
            , pat.national_id, pat.external_id, pat.birth_date, pat.gender 
            , o.short_name as organization_code, o.name AS organization_name
            , o.datim_org_code, o.datim_org_name 
            , a.completed_date,a.released_date,a.status_id as analysis_status_id, r.value as "Viral Load",a.type_of_sample_name,a.upload_flag,
            demo.samp_id samp_id,
            demo.arvtreatmentregime,
            demo.demandcd4count,
            demo.demandcd4date,
            demo.demandcd4percent,
            demo.hivstatus,
            demo.vlreasonforrequest,
            demo.vlpregnancy,
            demo.vlsuckle,
            currentARVTreatmentINNs.*, 
            dt.name as report_name, first_dt.report_generation_time, 
            dt.lastupdated as report_lastupdated,a.id analysis_id
            FROM patient as pat,
            sample_human as sh, sample_organization AS so, organization AS o 
            , 
            ( SELECT s.id AS samp_id, demo.*  FROM sample AS s LEFT JOIN 
            crosstab( 
            'SELECT DISTINCT oh.sample_id as samp_id, oht.type_name, value 
            FROM observation_history AS oh, sample AS s, observation_history_type AS oht 
            WHERE s.id = oh.sample_id AND oh.observation_history_type_id = oht.id order by 1;' 
            , 
            'SELECT DISTINCT oht.type_name FROM observation_history_type AS oht ORDER BY 1;' 
            ) 
            as demo (  "s_id" numeric(10) 
            ,aidsStage varchar(100) 
            ,antiTbTreatment varchar(100) 
            ,anyCurrentDiseases varchar(100) 
            ,anyPriorDiseases varchar(100) 
            ,anySecondaryTreatment varchar(100) 
            ,arvProphylaxis varchar(100) 
            ,arvProphylaxisBenefit varchar(100) 
            ,arvTreatmentAdvEffGrd varchar(100) 
            ,arvTreatmentAdvEffType varchar(100) 
            ,arvTreatmentAnyAdverseEffects varchar(100) 
            ,arvTreatmentChange varchar(100) 
            ,arvTreatmentInitDate varchar(100) 
            ,arvTreatmentNew varchar(100) 
            ,arvTreatmentRegime varchar(100) 
            ,billingRefNumber varchar(100) 
            ,cachexie varchar(100) 
            ,CCervCancer varchar(100) 
            ,CCrblToxo varchar(100) 
            ,CCryptoMen varchar(100) 
            ,cd4Count varchar(100) 
            ,cd4Percent varchar(100) 
            ,CDiarrheaC varchar(100) 
            ,CGenPrurigo varchar(100) 
            ,CIST varchar(100) 
            ,CKaposiSarc varchar(100) 
            ,clinicVisits varchar(100) 
            ,COpharCand varchar(100) 
            ,cotrimoxazoleTreatAdvEffGrd varchar(100) 
            ,cotrimoxazoleTreatAdvEffType varchar(100) 
            ,cotrimoxazoleTreatAnyAdvEff varchar(100) 
            ,cotrimoxazoleTreatment varchar(100) 
            ,cough varchar(100) 
            ,cryptoMen varchar(100) 
            ,CShingles varchar(100) 
            ,CTBExpul varchar(100) 
            ,CTBPul varchar(100) 
            ,currentARVTreatment varchar(100) 
            ,currentARVTreatmentINNs varchar(100) 
            ,currentDiseases varchar(100) 
            ,currentOITreatment varchar(100) 
            ,curvixC varchar(100) 
            ,demandcd4Count varchar(100) 
            ,demandcd4Date varchar(100) 
            ,demandcd4Percent varchar(100) 
            ,dermPruip varchar(100) 
            ,diarrhea varchar(100) 
            ,educationLevel varchar(100) 
            ,eidHowChildFed varchar(100) 
            ,eidInfantCotrimoxazole varchar(100) 
            ,eidInfantPTME varchar(100) 
            ,eidInfantsARV varchar(100) 
            ,eidInfantSymptomatic varchar(100) 
            ,eidMothersARV varchar(100) 
            ,eidMothersHIVStatus varchar(100) 
            ,eidStoppedBreastfeeding varchar(100) 
            ,eidTypeOfClinic varchar(100) 
            ,eidTypeOfClinicOther varchar(100) 
            ,expulTB varchar(100) 
            ,fever varchar(100) 
            ,futureARVTreatmentINNs varchar(100) 
            ,herpes varchar(100) 
            ,HIVDement varchar(100) 
            ,hivStatus varchar(100) 
            ,hospital varchar(100) 
            ,hospitalPatient varchar(100) 
            ,indFirstTestDate varchar(100) 
            ,indFirstTestName varchar(100) 
            ,indFirstTestResult varchar(100) 
            ,indSecondTestDate varchar(100) 
            ,indSecondTestName varchar(100) 
            ,indSecondTestResult varchar(100) 
            ,indSiteFinalResult varchar(100) 
            ,initcd4Count varchar(100) 
            ,initcd4Date varchar(100) 
            ,initcd4Percent varchar(100) 
            ,initialSampleCondition varchar(100) 
            ,interruptedARVTreatment varchar(100) 
            ,karnofskyScore varchar(100) 
            ,legalResidence varchar(100) 
            ,maritalStatus varchar(100) 
            ,matHIV varchar(100) 
            ,nameOfDoctor varchar(100) 
            ,nameOfRequestor varchar(100) 
            ,nameOfSampler varchar(100) 
            ,nationality varchar(100) 
            ,nextVisitDate varchar(100) 
            ,otherSecondaryOrderType varchar(100) 
            ,PatientRecordStatus varchar(100) 
            ,patientWeight varchar(100) 
            ,paymentStatus varchar(100) 
            ,PCervCancer varchar(100) 
            ,PCrblToxo varchar(100) 
            ,PCryptoMen varchar(100) 
            ,PDiarrheaC varchar(100) 
            ,PGenPrurigo varchar(100) 
            ,PIST varchar(100) 
            ,PKaposiSarc varchar(100) 
            ,POpharCand varchar(100) 
            ,primaryOrderType varchar(100) 
            ,priorARVTreatment varchar(100) 
            ,priorARVTreatmentINNs varchar(100) 
            ,priorCd4Date varchar(100) 
            ,priorDiseases varchar(100) 
            ,priorVLDate varchar(100) 
            ,priorVLLab varchar(100) 
            ,priorVLValue varchar(100) 
            ,program varchar(100) 
            ,projectFormName varchar(100) 
            ,PShingles varchar(100) 
            ,PTBExpul varchar(100) 
            ,PTBPul varchar(100) 
            ,pulTB varchar(100) 
            ,reason varchar(100) 
            ,reasonForSecondPCRTest varchar(100) 
            ,recInfect varchar(100) 
            ,recPneumon varchar(100) 
            ,referringSite varchar(100) 
            ,requestDate varchar(100) 
            ,sampleNature varchar(100) 
            ,SampleRecordStatus varchar(100) 
            ,sarcKapo varchar(100) 
            ,secondaryOrderType varchar(100) 
            ,secondaryTreatment varchar(100) 
            ,service varchar(100) 
            ,sespis varchar(100) 
            ,swallPaint varchar(100) 
            ,thrush varchar(100) 
            ,underInvestigation varchar(100) 
            ,vlBenefit varchar(100) 
            ,vlOtherReasonForRequest varchar(100) 
            ,vlPregnancy varchar(100) 
            ,vlReasonForRequest varchar(100) 
            ,vlSuckle varchar(100) 
            ,weightLoss varchar(100) 
            ,whichPCR varchar(100) 
            ,xIngPadenp varchar(100) 
            ,zona varchar(100)  ) 
            ON s.id = demo.s_id ORDER BY 1 
            ) AS demo
            ,
            ( SELECT s.id AS art_samp_id, currentARVTreatmentINNs.*  FROM sample AS s LEFT JOIN 
            crosstab(
            ' SELECT s.id as s_id, type, value FROM Sample AS s  LEFT JOIN 
            ( SELECT DISTINCT s.id as s_id , oh.observation_history_type_id AS type, oh.value AS value, oh.id  
            FROM Sample as s, Observation_History AS oh WHERE oh.sample_id = s.id  AND oh.observation_history_type_id = 
            (select id FROM observation_history_type WHERE type_name = ''currentARVTreatmentINNs'')  
            ORDER by 1,2, oh.id desc ) AS repeatCols ON s.id = repeatCols.s_id'
            ) AS currentARVTreatmentINNs ( s_id NUMERIC(10) , "currentARVTreatmentINNs1" VARCHAR(100), "currentARVTreatmentINNs2" VARCHAR(100), 
            "currentARVTreatmentINNs3" VARCHAR(100), "currentARVTreatmentINNs4" VARCHAR(100) )
            
            
            ON s.id = currentARVTreatmentINNs.s_id ORDER BY 1 
            ) AS currentARVTreatmentINNs
            ,  clinlims.analysis as a 
            LEFT JOIN  clinlims.result as r on r.analysis_id = a.id 
            LEFT JOIN  clinlims.sample_item as si on si.id = a.sampitem_id 
            LEFT JOIN  clinlims.sample as s on s.id = si.samp_id 
            LEFT JOIN  (select max(id)as id, row_id  from clinlims.document_track 
                    group by (row_id )  order by row_id DESC) as dtr on dtr.row_id=s.id 
            LEFT JOIN clinlims.document_track as dt on dtr.id=dt.id 
            LEFT JOIN  (select min(id)as id, row_id from clinlims.document_track 
            group by (row_id ) order by row_id ASC) as first_dtr on first_dtr.row_id=s.id 
            LEFT JOIN clinlims.document_track as first_dt on first_dtr.id=first_dt.id 
            WHERE 
            a.test_id =174
            AND a.sampitem_id = si.id
            AND s.id = si.samp_id
            AND s.id=sh.samp_id
            AND sh.patient_id=pat.id
            AND s.id=so.samp_id
            AND so.org_id=o.id
            AND s.id=demo.s_id
            AND s.id = currentARVTreatmentINNs.art_samp_id
            ORDER BY s.accession_number);