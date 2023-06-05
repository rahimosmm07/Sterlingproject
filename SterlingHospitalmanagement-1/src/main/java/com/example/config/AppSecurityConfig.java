package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Configuration
@EnableWebSecurity
@Service
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
	private static final String[] AUTH_WHITELIST = {
			// -- swagger ui
			"/v2/api-docs", "/swagger-resources/**", "/configuration/ui", "/configuration/security", "/swagger-ui.html",
			"/webjars/**" };

	@Autowired
	UserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService); // //
		// .setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();
		http.httpBasic().and()

				.authorizeRequests().antMatchers("/users", "/login").permitAll().antMatchers(HttpMethod.GET, "/doctor")
				.permitAll().antMatchers(HttpMethod.GET, "/appointment").hasRole("Reception").anyRequest()
				.authenticated().antMatchers(HttpMethod.POST, "/appointment").hasRole("Reception").anyRequest()
				.authenticated().antMatchers(HttpMethod.POST, "/doctor").hasRole("Reception").anyRequest()
				.authenticated();
		// .authenticated().antMatchers("/user/**").authenticated().antMatchers("/auth/**").authenticated()

		// .anyRequest().permitAll().and().formLogin().permitAll();//
	}

}
