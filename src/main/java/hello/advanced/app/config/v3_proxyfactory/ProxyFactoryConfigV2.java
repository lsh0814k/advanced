package hello.advanced.app.config.v3_proxyfactory;

import hello.advanced.app.V2.ControllerV2;
import hello.advanced.app.V2.RepositoryV2;
import hello.advanced.app.V2.ServiceV2;
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
public class ProxyFactoryConfigV2 {
    @Bean
    public ControllerV2 controllerV2(LogTrace logTrace) {
        ControllerV2 controller = new ControllerV2(serviceV2(logTrace));
        ProxyFactory factory = new ProxyFactory(controller);
        factory.addAdvisor(getAdvisor(logTrace));
        ControllerV2 proxy = (ControllerV2) factory.getProxy();
        log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), controller.getClass());
        return proxy;
    }

    @Bean
    public ServiceV2 serviceV2(LogTrace logTrace) {
        ServiceV2 service = new ServiceV2(repositoryV2(logTrace));
        ProxyFactory factory = new ProxyFactory(service);
        factory.addAdvisor(getAdvisor(logTrace));
        ServiceV2 proxy = (ServiceV2) factory.getProxy();
        log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), service.getClass());
        return proxy;
    }

    @Bean
    public RepositoryV2 repositoryV2(LogTrace logTrace) {
        RepositoryV2 repository = new RepositoryV2();
        ProxyFactory factory = new ProxyFactory(repository);
        factory.addAdvisor(getAdvisor(logTrace));
        RepositoryV2 proxy = (RepositoryV2) factory.getProxy();
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
