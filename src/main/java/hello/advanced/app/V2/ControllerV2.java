package hello.advanced.app.V2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerV2 {
    private final ServiceV2 serviceV2;

    public ControllerV2(ServiceV2 serviceV2) {
        this.serviceV2 = serviceV2;
    }

    @GetMapping("/proxy/v2/request")
    public String request(String itemId) {
        serviceV2.orderItem(itemId);
        return "ok";
    }

    @GetMapping("/proxy/v2/no-log")
    public String noLog() {
        return "ok";
    }
}
