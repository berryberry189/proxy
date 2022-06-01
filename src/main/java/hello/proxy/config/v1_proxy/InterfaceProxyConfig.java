package hello.proxy.config.v1_proxy;

import hello.proxy.app.v1.*;
import hello.proxy.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceProxyConfig {

    // 스프링 컨테이너에 프록시 객체가 등록된다. ( 실제 객체가 아닌 프록시 객체를 스프링 빈으로 관리한다.)
    // 이때, 실제 객체는 스프링 컨테이너와는 상관이 없으며 프록시 객체를 통해서 참조될 뿐이다.
    // => 따라서 프록시 객체는 스프링 컨테이너가 관리하고 자바 힙메모리에도 올라간다.
    // 반면 실제 객체는 힙 메모리에는 올라가지만 스프링 컨테이너가 관리하지는 않는다.


    // 추가 설명
    // 이론적으로는 모든 객체에 인터페이스를 도입해서 역할과 구현을 나누는 것이 좋지만,
    // 실제로는 구현을 거의 변경할 일이 없는 클래스도 많다.
    // 구현을 변경할 일이 없는 코드에 무작정 인터페이스를 사용하는 것은 번거롭고 실용적이지 않으므로 이런 경우에는 구체 클래스를 바로 사용하는 것이 좋다.
    // 인터페이스가 있는 경우 & 구체클래스가 있는 경우 두가지 상황을 모두 대응할 수 있어야 한다.


    @Bean // 빈 이름 : orderController, 빈 객체 : OrderControllerInterfaceProxy
    public OrderControllerV1 orderController(LogTrace logTrace){
        OrderControllerV1Impl orderControllerImpl = new OrderControllerV1Impl(orderService(logTrace));
        return new OrderControllerInterfaceProxy(orderControllerImpl, logTrace);
    }

    @Bean
    public OrderServiceV1 orderService(LogTrace logTrace) {
        OrderServiceV1Impl orderServiceImpl = new OrderServiceV1Impl(orderRepository(logTrace));
        return new OrderServiceInterfaceProxy(orderServiceImpl, logTrace);
    }

    @Bean
    public OrderRepositoryV1 orderRepository(LogTrace logTrace) {
        OrderRepositoryV1Impl orderRepositoryImpl = new OrderRepositoryV1Impl();
        return new OrderRepositoryInterfaceProxy(orderRepositoryImpl, logTrace);
    }

}
