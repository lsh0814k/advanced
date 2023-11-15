package hello.advanced.app.config.v1_proxy.concrete_proxy;

import hello.advanced.app.V2.ControllerV2;
import hello.advanced.app.V2.ServiceV2;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;

public class ControllerConcreteProxy extends ControllerV2 {
    private final ControllerV2 target;
    private final LogTrace logTrace;

    public ControllerConcreteProxy(ControllerV2 target, LogTrace logTrace) {
        super(null);
        this.target = target;
        this.logTrace = logTrace;
    }

    @Override
    public String request(String itemId) {
        TraceStatus status = null;
        try {
            status = logTrace.begin("ControllerV1.request()");
            String result = target.request(itemId);
            logTrace.end(status);

            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }

    @Override
    public String noLog() {
        return target.noLog();
    }
}
