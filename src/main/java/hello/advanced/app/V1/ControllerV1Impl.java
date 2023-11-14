package hello.advanced.app.V1;

public class ControllerV1Impl implements ControllerV1 {
    private final ServiceV1 serviceV1;

    public ControllerV1Impl(ServiceV1 serviceV1) {
        this.serviceV1 = serviceV1;
    }

    @Override
    public String request(String itemId) {
        serviceV1.orderItem(itemId);
        return "ok";
    }

    @Override
    public String noLog() {
        return "ok";
    }
}
