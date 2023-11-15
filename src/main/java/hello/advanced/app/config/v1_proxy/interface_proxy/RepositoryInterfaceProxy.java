package hello.advanced.app.config.v1_proxy.interface_proxy;

import hello.advanced.app.V1.RepositoryV1;
import hello.advanced.app.V1.RepositoryV1Impl;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RepositoryInterfaceProxy implements RepositoryV1 {
    private final RepositoryV1Impl target;
    private final LogTrace logTrace;


    @Override
    public void save(String itemId) {
        TraceStatus status = null;
        try {
            status = logTrace.begin("RepositoryV1.save()");
            target.save(itemId);
            logTrace.end(status);
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
