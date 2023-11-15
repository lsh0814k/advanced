package hello.advanced.app.config.v1_proxy;

import hello.advanced.app.V2.ControllerV2;
import hello.advanced.app.V2.RepositoryV2;
import hello.advanced.app.V2.ServiceV2;
import hello.advanced.app.config.v1_proxy.concrete_proxy.ControllerConcreteProxy;
import hello.advanced.app.config.v1_proxy.concrete_proxy.RepositoryConcreteProxy;
import hello.advanced.app.config.v1_proxy.concrete_proxy.ServiceConcreteProxy;
import hello.advanced.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ConcreteProxyConfig {

    @Bean
    @Primary
    public ControllerV2 controllerV2(LogTrace logTrace) {
        return new ControllerConcreteProxy(new ControllerV2(serviceV2(logTrace)), logTrace);
    }

    @Bean
    public ServiceV2 serviceV2(LogTrace logTrace) {
        return new ServiceConcreteProxy(new ServiceV2(repositoryV2(logTrace)), logTrace);
    }

    @Bean
    public RepositoryV2 repositoryV2(LogTrace logTrace) {
        return new RepositoryConcreteProxy(new RepositoryV2(), logTrace);
    }


}
