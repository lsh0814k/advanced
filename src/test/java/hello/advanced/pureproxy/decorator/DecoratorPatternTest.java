package hello.advanced.pureproxy.decorator;

import hello.advanced.pureproxy.decorator.code.Component;
import hello.advanced.pureproxy.decorator.code.DecoratorPatternClient;
import hello.advanced.pureproxy.decorator.code.RealComponent;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class DecoratorPatternTest {

    @Test
    void noDecorator() {
        Component component = new RealComponent();
        DecoratorPatternClient client = new DecoratorPatternClient(component);
        client.execute();
    }
}
