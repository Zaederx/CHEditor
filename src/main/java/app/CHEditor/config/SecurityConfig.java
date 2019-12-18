package app.CHEditor.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	public void configure(HttpSecurity https) throws Exception {
		https
			.requiresChannel()
			.anyRequest()
			.requiresSecure()
		.and()
			.authorizeRequests().antMatchers("/","/cheditor/**")
			;
	}
	
}
