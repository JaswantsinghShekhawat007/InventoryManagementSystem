package com.gateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import com.gateway.exception.AuthorizationDeniedException;
import com.gateway.exception.MissingAuthorizationHeaderException;
import com.gateway.security.JwtTokenProvider;
import com.google.common.net.HttpHeaders;

@Component
public class JwtGatewayAuthenticationFilter
		extends AbstractGatewayFilterFactory<JwtGatewayAuthenticationFilter.Config> {

	@Autowired
	private RouteValidater validator;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	public JwtGatewayAuthenticationFilter() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange, chain) -> {

			if (validator.isSecured.test(exchange.getRequest())) {
				// header contains token or not
				if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					throw new MissingAuthorizationHeaderException("Missing Authorization Header");
				}

				String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				
				if(authHeader!=null && authHeader.startsWith("Bearer ")) {
					authHeader= authHeader.substring(7);
				}
				try {
					//REST call to Auth Service
					jwtTokenProvider.validateToken(authHeader);
					
				}
				catch(Exception e) {
					throw new AuthorizationDeniedException("Authorization Denied Invalid Token");
				}
			}

			return chain.filter(exchange);
		});
	}

	public static class Config {

	}

}
