package hello.advanced.app.config.v2_dynamicproxy;

import hello.advanced.app.V1.*;
import hello.advanced.app.config.v2_dynamicproxy.handler.LogTraceBasicHandler;
import hello.advanced.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
public class DynamicProxyBasicConfig {

    @Bean
    public ControllerV1 controllerV1(LogTrace logTrace) {
        ControllerV1 controllerV1 = new ControllerV1Impl(serviceV1(logTrace));
        LogTraceBasicHandler handler = new LogTraceBasicHandler(controllerV1, logTrace);

        return (ControllerV1) Proxy.newProxyInstance(ControllerV1.class.getClassLoader(), new Class[]{ControllerV1.class}, handler);
    }

    @Bean
    public ServiceV1 serviceV1(LogTrace logTrace) {
        ServiceV1 serviceV1 = new ServiceV1Impl(repositoryV1(logTrace));
        LogTraceBasicHandler handler = new LogTraceBasicHandler(serviceV1, logTrace);

        return (ServiceV1) Proxy.newProxyInstance(ServiceV1.class.getClassLoader(), new Class[]{ServiceV1.class}, handler);
    }

    @Bean
    public RepositoryV1 repositoryV1(LogTrace logTrace) {
        RepositoryV1 repository = new RepositoryV1Impl();
        LogTraceBasicHandler handler = new LogTraceBasicHandler(repository, logTrace);

        return (RepositoryV1) Proxy.newProxyInstance(RepositoryV1.class.getClassLoader(), new Class[]{RepositoryV1.class}, handler);
    }
}
