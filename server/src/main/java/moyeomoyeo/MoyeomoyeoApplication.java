package moyeomoyeo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MoyeomoyeoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoyeomoyeoApplication.class, args);
	}

}
