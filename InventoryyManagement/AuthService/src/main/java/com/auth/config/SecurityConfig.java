package com.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth.security.CustomUserDetailsService;
import com.auth.security.JwtAuthenticationEntryPoint;
import com.auth.security.JwtAuthenticationFilter;


@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig  {
	
	private UserDetailsService userDetailsService;
	
	private JwtAuthenticationEntryPoint authenticationEntryPoint;
	
	private JwtAuthenticationFilter authenticationFilter;
	
	
//	@Bean
//	UserDetailsService userDetailsService( PasswordEncoder passwordEncoder ) {
//		
//		UserDetails admin = User.builder()
//				.username("admin")
//				.password(passwordEncoder.encode("admin"))
//				.roles("ADMIN")
//				.build();
//		
//		UserDetails merchant = User.builder()
//				.username("merchant25653")
//				.password(passwordEncoder.encode("merchant2@123"))
//				.roles("MERCHANT")
//				.build();
//		
//		return new InMemoryUserDetailsManager(admin,merchant);
//	}
	
	

	@Autowired
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	@Autowired
	public void setAuthenticationEntryPoint(JwtAuthenticationEntryPoint authenticationEntryPoint) {
		this.authenticationEntryPoint = authenticationEntryPoint;
	}
	
	@Autowired
	public void setAuthenticationFilter(JwtAuthenticationFilter authenticationFilter) {
		this.authenticationFilter = authenticationFilter;
	}
	
	

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		http.csrf((csrf) -> csrf.disable())
				.authorizeHttpRequests( (authorize) -> {
					authorize.requestMatchers("/api/auth/**").permitAll();
//					authorize.requestMatchers("/api/auth/merchant/**").permitAll();
//					authorize.requestMatchers("/api/auth/admin/**").permitAll();
					authorize.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
					authorize.anyRequest().authenticated();
				}).httpBasic( Customizer.withDefaults() );
		
		
		http.exceptionHandling( exception -> exception
				.authenticationEntryPoint(authenticationEntryPoint));
		
		http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
		
	}
}
