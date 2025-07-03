package com.kh.finalproject.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.kh.finalproject.security.filter.JwtFilter;

import lombok.RequiredArgsConstructor;

@EnableMethodSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfigure {

	private final JwtFilter jwtFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)  throws Exception  {
		
		return httpSecurity.formLogin(AbstractHttpConfigurer::disable)
	              		   .httpBasic(AbstractHttpConfigurer::disable)
	                       .csrf(AbstractHttpConfigurer::disable)
	                       .cors(Customizer.withDefaults())
	                       .authorizeHttpRequests(request -> {
							   request.requestMatchers("/admin/**").hasRole("ADMIN");
							  
							   request.requestMatchers(HttpMethod.POST,
									   "/api/auth/tokens",
									   "/api/users",
									   "/api/users/**",
									   "/api/auth/**",
									   "/api/favorite/",
									   "/api/favorite/**",
									   "/api/favoriteList",
									   "/api/festivals/**",
										 "/api/systm/reports"
										 ).permitAll();
							   request.requestMatchers(HttpMethod.POST,
									   "/api/region",
									   "/api/festivals",
									   "/api/systm/penaltys",
									   "/api/systm/reportCategorys"
										 ).hasRole("ADMIN");
							  
							   request.requestMatchers(HttpMethod.DELETE,
									  "/api/auth/**").permitAll();
							   
							   request.requestMatchers(HttpMethod.DELETE,
									   "/api/users",
									   "/api/users/delete",
									   "/api/commnet/**").authenticated();
							   request.requestMatchers(HttpMethod.DELETE,
									  "/api/region/**",
									  "/api/festivals",
									  "/api/festivals/**",
									  "/api/lodgings",
									  "/api/diners",
										"/api/systm/**"
										).hasRole("ADMIN");
							  
							   
							   request.requestMatchers(HttpMethod.PUT, 
									   "/api/users/update-nickname",
									   "/api/users/update-pw",
										  "/api/users/**").authenticated();
							   request.requestMatchers(HttpMethod.PUT,
										  "/api/region/**",
										  "/api/festivals",
										  "/api/lodgings",
										  "/api/lodgings/**",
										  "/api/diners",
										  "/api/diners/**",
											"/api/systm/**"
											).hasRole("ADMIN");
							   
							   request.requestMatchers(HttpMethod.GET,
									   "/api/users/check-id",
									   "/api/region/**",
									   "/api/users/comments",
									   "/api/users/festivals",
									   "/api/lodgings",
									   "/api/diners",
									   "/api/diners/**",
										 "/api/systm/reportCategorys"
										 ).permitAll();
							   request.requestMatchers(HttpMethod.GET,
									   "/api/users/comments",
										  "/api/users/**",
											"/api/systm/reports"
										).authenticated();
							   request.requestMatchers(HttpMethod.GET,
									   "/api/systm/penaltys"
										).hasRole("ADMIN");
	                       })
	                       
	                      .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
						   .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
						   .build();
							
		
		
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		
		return authConfig.getAuthenticationManager();
	}
	
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		
		CorsConfiguration configuration = new CorsConfiguration();
		
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));	
		//configuration.setAllowCredentials(true);
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		
		source.registerCorsConfiguration("/**", configuration);
		
		return source;
	}
	
	
	
	
	
	
	
}