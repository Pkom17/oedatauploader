package ci.itech.oedatauploader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class OedatauploaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OedatauploaderApplication.class, args);
	}

}
