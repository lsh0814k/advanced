package hello.advanced.app.V1;

import org.springframework.web.bind.annotation.*;

@RestController
public interface ControllerV1 {
    @GetMapping("/proxy/v1/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("/proxy/v1/no-log")
    String noLog();
}
