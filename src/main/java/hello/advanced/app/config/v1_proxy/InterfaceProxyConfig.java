package hello.advanced.app.config.v1_proxy;

import hello.advanced.app.V1.*;
import hello.advanced.app.config.v1_proxy.interface_proxy.ControllerInterfaceProxy;
import hello.advanced.app.config.v1_proxy.interface_proxy.RepositoryInterfaceProxy;
import hello.advanced.app.config.v1_proxy.interface_proxy.ServiceInterfaceProxy;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceProxyConfig {
    @Bean
    public ControllerV1 controllerV1(LogTrace logTrace) {
        return new ControllerInterfaceProxy(new ControllerV1Impl(serviceV1(logTrace)), logTrace);
    }

    @Bean
    public ServiceV1 serviceV1(LogTrace logTrace) {
        return new ServiceInterfaceProxy(new ServiceV1Impl(repositoryV1(logTrace)), logTrace);

    }

    @Bean
    public RepositoryV1 repositoryV1(LogTrace logTrace) {
        return new RepositoryInterfaceProxy(new RepositoryV1Impl(), logTrace);
    }
}
