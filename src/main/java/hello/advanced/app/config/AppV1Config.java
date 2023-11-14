package hello.advanced.app.config;

import hello.advanced.app.V1.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppV1Config {

    @Bean
    public ControllerV1 controllerV1() {
        return new ControllerV1Impl(serviceV1());
    }

    @Bean
    public ServiceV1 serviceV1() {
        return new ServiceV1Impl(repositoryV1());
    }

    @Bean
    public RepositoryV1 repositoryV1() {
        return new RepositoryV1Impl();
    }
}
