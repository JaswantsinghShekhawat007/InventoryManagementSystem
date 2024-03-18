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
//import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth.security.JwtAuthenticationEntryPoint;
import com.auth.security.JwtAuthenticationFilter;


@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig  {
	
	@SuppressWarnings("unused")
	private UserDetailsService userDetailsService;
	
	private JwtAuthenticationEntryPoint authenticationEntryPoint;
	
	private JwtAuthenticationFilter authenticationFilter;	
	

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
		
		http
				.csrf((csrf) -> csrf.disable())
				.authorizeHttpRequests( (authorize) -> {
					authorize.requestMatchers("/api/auth/**").permitAll();
					authorize.requestMatchers("/api/auth-admin/**").permitAll();
					authorize.requestMatchers(HttpMethod.OPTIONS, "/api/auth/**").permitAll();
					authorize.requestMatchers(HttpMethod.OPTIONS, "/api/auth-admin/**").permitAll();
					authorize.anyRequest().authenticated();
				}).httpBasic( Customizer.withDefaults() );
		
		
		http.exceptionHandling( exception -> exception
				.authenticationEntryPoint(authenticationEntryPoint));
		
		http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
		
//		http.sessionManagement(
//						management->management.sessionCreationPolicy(
//								SessionCreationPolicy.STATELESS)
//						).authorizeHttpRequests(
//								Authroize->Authroize.requestMatchers("/api/**").authenticated().anyRequest().permitAll()
//						).addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
//						 .csrf(csrf->csrf.disable())
//						 .cors(cors->cors.configurationSource(corsConfigurationSource()))
//						 .httpBasic(Customizer.withDefaults())
//						 .formLogin(Customizer.withDefaults());
//		 
//						return http.build();
//						/api/auth
		
	}
}
