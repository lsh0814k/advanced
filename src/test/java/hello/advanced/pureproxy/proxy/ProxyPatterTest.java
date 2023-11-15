package hello.advanced.pureproxy.proxy;

import hello.advanced.pureproxy.proxy.code.ProxyPatternClient;
import hello.advanced.pureproxy.proxy.code.RealSubject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ProxyPatterTest {

    @Test
    void noProxyTest() {
        ProxyPatternClient client = new ProxyPatternClient(new RealSubject());
        client.execute();
        client.execute();
        client.execute();
    }
}
