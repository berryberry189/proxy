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
