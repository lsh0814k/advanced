package hello.advanced.app.config.v1_proxy.interface_proxy;

import hello.advanced.app.V1.ServiceV1;
import hello.advanced.app.V1.ServiceV1Impl;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ServiceInterfaceProxy implements ServiceV1 {
    private final ServiceV1Impl target;
    private final LogTrace logTrace;

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

