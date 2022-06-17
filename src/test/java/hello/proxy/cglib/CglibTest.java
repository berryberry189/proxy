package hello.proxy.cglib;

import hello.proxy.cglib.code.TimeMethodInterceptor;
import hello.proxy.common.service.ConcreteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j
public class CglibTest {

    // 리플렉션 - 메타 데이터
    // jdk 동적 프록시 - 직접 프록시를 만들지 않고 실시간으로 동적으로 프록시를 만들어냄 (InvocationHandler 를 통해)
        // 인터페이스 사용
        // 클라이언트 -> 프록시 호출 -> 인보케이션 핸들러 호출 -> 핸들러가 알아서 구현체 호출
    // cglib - 바이트 코드를 조작해서 동적으로 클래스를 생성하는 기술을 제공
        // 인터페이스 없이 구체클래스만 가지고 동적 프록시 생성 가능


    @Test
    void cglib(){
        ConcreteService target = new ConcreteService();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ConcreteService.class); // 부모타입 설정
        enhancer.setCallback(new TimeMethodInterceptor(target));
        ConcreteService proxy = (ConcreteService) enhancer.create(); // 프록시 생성
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());

        proxy.call();
    }
}
