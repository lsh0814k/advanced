package hello.advanced.app.config.v1_proxy.concrete_proxy;

import hello.advanced.app.V2.RepositoryV2;
import hello.advanced.app.V2.ServiceV2;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;

public class ServiceConcreteProxy extends ServiceV2 {
    private final ServiceV2 target;
    private final LogTrace logTrace;

    public ServiceConcreteProxy(ServiceV2 target, LogTrace logTrace) {
        super(null);
        this.target = target;
        this.logTrace = logTrace;
    }

    @Override
    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = logTrace.begin("ServiceV1.orderItem()");
            target.orderItem(itemId);
            logTrace.end(status);
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
