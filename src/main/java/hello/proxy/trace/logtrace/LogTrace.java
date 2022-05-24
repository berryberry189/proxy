package hello.proxy.trace.logtrace;

import hello.proxy.trace.TraceStatus;

// 로그 추척기
public interface LogTrace {

    TraceStatus begin(String message);

    void end(TraceStatus status);

    void exception(TraceStatus status, Exception e);
}
