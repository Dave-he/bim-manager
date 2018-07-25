package com.mjwise.demo.config;


import com.mjwise.demo.component.LoginHandlerInterceptor;
import com.mjwise.demo.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 使用WebMvcConfigurerAdapter可以来扩展SpringMVC的功能
 * 原理：
 *      1.WebMvcConfigurerAdapters是SpringMVC的自动配置类
 *      2.在做其他自动配置时会导入；@Improt(EnableWebMvcConfiguration.class)
 *      3.@Autowired(requireds = false)
 *
 * @EnableWebMvc 全面接管springmvc
 */

//@EnableWebMvc

@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter{

//	@Override
//	public void addViewControllers(ViewControllerRegistry registry){
//		//super.addViewControllers(registry);
//		//浏览器发送/atguigu请求来到success
//		registry.addViewController("/atguigu").setViewName("success");
//	}

	@Bean
	//将组件注册进入容器中
	public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
		WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter(){
			@Override
			public void addViewControllers(ViewControllerRegistry registry){
				registry.addViewController("/login.html").setViewName("login");
//				registry.addViewController("/index.html").setViewName("login");
				registry.addViewController("/home.html").setViewName("home");
//				registry.addViewController("/register.html").setViewName("register");
				super.addViewControllers(registry);
			}

			//注册拦截器
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				//super.addInterceptors(registry);
				//静态资源： *.css,*.js

				registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/login");
			}
		};
		return adapter;
	}

	@Bean
	public LocaleResolver localeResolver(){
		return new MyLocaleResolver();
	}
}
