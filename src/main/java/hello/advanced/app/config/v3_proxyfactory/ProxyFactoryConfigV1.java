package hello.advanced.app.config.v3_proxyfactory;

import hello.advanced.app.V1.*;
import hello.advanced.app.config.v3_proxyfactory.advice.LogTraceAdvice;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ProxyFactoryConfigV1 {

    @Bean
    public ControllerV1 controllerV1(LogTrace logTrace) {
        ControllerV1 controller = new ControllerV1Impl(serviceV1(logTrace));
        ProxyFactory factory = new ProxyFactory(controller);
        factory.addAdvisor(getAdvisor(logTrace));
        ControllerV1 proxy = (ControllerV1) factory.getProxy();
        log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), controller.getClass());
        return proxy;
    }

    @Bean
    public ServiceV1 serviceV1(LogTrace logTrace) {
        ServiceV1 service = new ServiceV1Impl(repositoryV1(logTrace));
        ProxyFactory factory = new ProxyFactory(service);
        factory.addAdvisor(getAdvisor(logTrace));
        ServiceV1 proxy = (ServiceV1) factory.getProxy();
        log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), service.getClass());
        return proxy;
    }

    @Bean
    public RepositoryV1 repositoryV1(LogTrace logTrace) {
        RepositoryV1 repository = new RepositoryV1Impl();
        ProxyFactory factory = new ProxyFactory(repository);
        factory.addAdvisor(getAdvisor(logTrace));
        RepositoryV1 proxy = (RepositoryV1) factory.getProxy();
        log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), repository.getClass());
        return proxy;
    }

    private Advisor getAdvisor(LogTrace logTrace) {
        // Pointcut
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");

        // advice
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);

        return new DefaultPointcutAdvisor(pointcut, advice);
    }
}
