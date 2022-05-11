package hello.proxy;

import hello.proxy.config.AppV1Config;
import hello.proxy.config.AppV2Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

// @Import(AppV1Config.class) // 스프인 빈 등록
@Import(AppV2Config.class)
@SpringBootApplication(scanBasePackages = "hello.proxy.app") // 컴포넌트 스텐 범위 지정
public class ProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}

}
