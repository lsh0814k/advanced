package hello.advanced.app.config;

import hello.advanced.app.V2.RepositoryV2;
import hello.advanced.app.V2.ServiceV2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Controller는 @RestController 애노테이션이 있기 때문에 자동으로 빈에 등록이 된다.
 */
@Configuration
public class AppV2Config {

    @Bean
    public ServiceV2 serviceV2() {
        return new ServiceV2(repositoryV2());
    }

    @Bean
    public RepositoryV2 repositoryV2() {
        return new RepositoryV2();
    }
}
