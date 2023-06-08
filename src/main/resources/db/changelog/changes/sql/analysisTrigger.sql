CREATE OR REPLACE FUNCTION update_analysis_on_update() RETURNS trigger  as $$
BEGIN
IF (to_jsonb(NEW) ? 'upload_flag') = FALSE THEN 
-- IF(NEW.upload_flag = OLD.upload_flag) THEN
    UPDATE analysis set upload_flag = 'TO_UPDATE' WHERE id = OLD.id;
-- END IF;
END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION update_analysis_on_sample_update() RETURNS trigger  as $$
BEGIN
    UPDATE analysis set upload_flag = 'TO_UPDATE' WHERE sampitem_id = (SELECT id FROM sample_item WHERE samp_id = OLD.id);
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER analysis_change_trigger
  AFTER UPDATE
  ON analysis
  FOR EACH ROW
  EXECUTE PROCEDURE update_analysis_on_update();

  create TRIGGER sample_change_trigger
  AFTER UPDATE
  ON sample
  FOR EACH ROW
  EXECUTE PROCEDURE update_analysis_on_sample_update();