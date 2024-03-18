package com.gateway.filter;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouteValidater {

	
	public static final List<String> openApiEndpoints = List.of(
			
			"/eureka",
			"/api/auth/merchant/register",
			"/api/auth/login"
			
	);
	
	public Predicate<ServerHttpRequest> isSecured = 
				request -> {
						String path = request.getURI().getPath();
						HttpMethod method = request.getMethod();
						boolean isPreflightRequest = HttpMethod.OPTIONS.equals(method);
						return !isPreflightRequest || openApiEndpoints.stream().noneMatch(path::contains);
					};
	
	
//						openApiEndpoints.stream()
//						.noneMatch(uri -> request.getURI().getPath().contains(uri));
		
			
	
}
