package hello.advanced;

import hello.advanced.app.config.AppV1Config;
import hello.advanced.app.config.AppV2Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;



@Import({AppV1Config.class, AppV2Config.class})
@SpringBootApplication(scanBasePackages = "hello.advanced.app.V3")
//@SpringBootApplication(scanBasePackages = "hello.advanced.app.V2")
// @SpringBootApplication(scanBasePackages = "hello.advanced.app.V1")
public class AdvancedApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvancedApplication.class, args);
	}

}
