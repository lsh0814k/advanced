package hello.advanced.app.V3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerV3 {
    private final ServiceV3 serviceV3;

    public ControllerV3(ServiceV3 serviceV3) {
        this.serviceV3 = serviceV3;
    }

    @GetMapping("/proxy/v3/request")
    public String request(String itemId) {
        serviceV3.orderItem(itemId);
        return "ok";
    }

    @GetMapping("/proxy/v3/no-log")
    public String noLog() {
        return "ok";
    }
}
