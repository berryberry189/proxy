package hello.proxy.trace;

public class TraceStatus {
    // 로그를 시작할때의 상태정보를 가지고 있다. 이 상태 정보는 로그를 종료할 때 사용된다.

    private TraceId traceId;
    private Long startTimeMs; // 로그 시작 시간
    private String message; // 시작시 사용 메세지

    public TraceStatus(TraceId traceId, Long startTimeMs, String message) {
        this.traceId = traceId;
        this.startTimeMs = startTimeMs;
        this.message = message;
    }

    public TraceId getTraceId() {
        return traceId;
    }

    public Long getStartTimeMs() {
        return startTimeMs;
    }

    public String getMessage() {
        return message;
    }
}
