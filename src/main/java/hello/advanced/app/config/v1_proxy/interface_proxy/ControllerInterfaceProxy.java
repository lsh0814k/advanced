package hello.advanced.app.config.v1_proxy.interface_proxy;

import hello.advanced.app.V1.ControllerV1;
import hello.advanced.app.V1.ControllerV1Impl;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ControllerInterfaceProxy implements ControllerV1 {
    private final ControllerV1Impl target;
    private final LogTrace logTrace;

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
