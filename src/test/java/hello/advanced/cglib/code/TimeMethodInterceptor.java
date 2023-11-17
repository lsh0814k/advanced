package hello.advanced.cglib.code;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@Slf4j
public class TimeMethodInterceptor implements MethodInterceptor {

    private final Object target;

    public TimeMethodInterceptor(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        log.info("timeProxy 실행");
        long start = System.currentTimeMillis();

        log.info("aaaaaaaaaaaaaaaaa = {}", target.getClass());
        Object result = proxy.invoke(target, args);

        long end = System.currentTimeMillis();
        long resultTime = end - start;
        log.info("TimeProxy 종료 resultTime={}", resultTime);
        return result;
    }
}
