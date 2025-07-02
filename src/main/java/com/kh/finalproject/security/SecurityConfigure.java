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
									   "/users",
									   "/users/**",
									   "/auth/**",
									   "/favorite/",
									   "/favorite/**",
									   "/favoriteList",
									   "/festivals/**").permitAll();
							   request.requestMatchers(HttpMethod.POST,
									   "/region",
									   "/festivals",
									   "/systm/**").hasRole("ADMIN");
							  
							   request.requestMatchers(HttpMethod.DELETE,
									  "/auth/**").permitAll();
							   
							   request.requestMatchers(HttpMethod.DELETE, 
									   "/users",
									   "/commnet/**").authenticated();
							   request.requestMatchers(HttpMethod.DELETE,
									  "/region/**",
									  "/festivals",
									  "/festivals/**",
									  "/lodgings",
									  "/diners").hasRole("ADMIN");
							  
							   request.requestMatchers(HttpMethod.PUT,
										 "/comment").permitAll();
							   request.requestMatchers(HttpMethod.PUT, 
										  "/users/**").authenticated();
							   request.requestMatchers(HttpMethod.PUT,
										  "/region/**",
										  "/festivals",
										  "/lodgings",
										  "/lodgings/**",
										  "/diners",
										  "/diners/**").hasRole("ADMIN");
							   
							   request.requestMatchers(HttpMethod.GET,
									   "/region/**",
									   "/commnet/**",
									   "/festivals",
									   "/lodgings",
									   "/diners",
									   "/diners/**").permitAll();
							   request.requestMatchers(HttpMethod.GET, 
										  "/users/**"
										).authenticated();
							   
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
		configuration.setAllowCredentials(true);
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		
		source.registerCorsConfiguration("/**", configuration);
		
		return source;
	}
	
	
	
	
	
	
	
}