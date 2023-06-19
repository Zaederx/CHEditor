package app.CHEditor.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry 
		.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/images/");
		registry.addResourceHandler("/jqtree/**").addResourceLocations("classpath:/jqTree/");
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");

		WebMvcConfigurer.super.addResourceHandlers(registry);
	}
}
