package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeDecorator implements Component{

    private final Component component;

    public TimeDecorator(Component component) {
        this.component = component;
    }

    @Override
    public String operation() { // 실행시간 측정
        log.info("TimeDecorator 실행");
        long startTime = System.currentTimeMillis();
        String result = component.operation();
        long endTime = System.currentTimeMillis();
        log.info("TimeDecorator 종료 resultTime={}ms", endTime - startTime );
        return result;
    }
}
