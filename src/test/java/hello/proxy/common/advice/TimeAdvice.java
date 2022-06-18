package hello.proxy.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class TimeAdvice implements MethodInterceptor {

    // 타켓 필요없음 => 프록시 팩토리에서 넣어줌. MethodInvocation 에 존재


    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();

        Object result = invocation.proceed(); // 해당 로직에서 타켓을 알아서 찾고 결과값을 호출받음

        long endTime = System.currentTimeMillis();
        log.info("TimeProxy 종료 resultTime={}ms", endTime - startTime );

        return result;
    }
}
