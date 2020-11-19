package com.udec.exception;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableResourceServer
public class AuthExceptionWrapper implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			org.springframework.security.core.AuthenticationException authException)
			throws IOException, ServletException {
		final Map<String, Object> mapException = new HashMap<>();

		authException.printStackTrace();

		mapException.put("error", "401");
		mapException.put("message", "No está autorizado para acceder a este recurso");
		mapException.put("exception", authException.getMessage());
		mapException.put("path", request.getServletPath());
		mapException.put("timestamp", LocalDateTime.now());

		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		final ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), mapException);

	}

}
