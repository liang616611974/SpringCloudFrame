package com.liangfeng.study.api.config;
import java.lang.reflect.Method;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import com.netflix.hystrix.HystrixCommand.Setter;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;

import feign.Feign;
import feign.Logger;
import feign.Target;
import feign.hystrix.HystrixFeign;
import feign.hystrix.SetterFactory;

/**
 * @Title RcFeignConfig.java
 * @Description Feign 配置类，该类不能包含在主应用程序上下文的@ComponentScan中，否则该类中的配置信息就会被
 				  所有的@FeignClient共享。因此，如果只想自定义某个Feign客户端配置，必须方式@Configuration注解的类
                  所在的包与@ComponenScan扫描的包重叠，或显式指定@ComponenScan不扫描@Configuration类所在的包
 * @version 1.0
 * @author Liangfeng
 * @date 2018/9/1 0001 下午 11:34
 */
//@Configuration
public class RcFeignConfig {

	/**
	 * Feign 日志配置，日志打印只会对DEBUG级别做出相应。
	 * NONE, 不记录日志 (默认)。
	 * BASIC, 只记录请求方法和URL以及响应状态代码和执行时间。
	 * HEADERS, 记录请求和应答的头的基本信息。
	 * FULL, 记录请求和响应的头信息，正文和元数据。
	 * @return
	 */
	@Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
	}

	/**
	 * 契约配置,Feign默认使用的契约是SpringMvcContract,因为他可以使用SpringMVC的注解
	 * @return
	 */
	/*@Bean
	public Contract feignContract() {
		return new feign.Contract.Default();
	}*/
	
	@Bean
	@Scope("prototype")
	public Feign.Builder feignBuilder() {
		class RcSetterFactory implements SetterFactory {
			@Override
			public Setter create(Target<?> target, Method method) {
				String groupKey = target.name();
				String commandKey = method.getName();
				return Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupKey))
						.andCommandKey(HystrixCommandKey.Factory.asKey(commandKey));
			}
		}
		return HystrixFeign.builder().setterFactory(new RcSetterFactory());
		// return Feign.builder();
	}

}
