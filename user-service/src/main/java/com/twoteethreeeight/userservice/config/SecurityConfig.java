package com.twoteethreeeight.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http

				.csrf(csrf -> csrf.disable())
				.sessionManagement(httpSecutityManagementConfiggure -> {
					httpSecutityManagementConfiggure
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
				})
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers("/", "public", "/api/**", "/api/registration/**","user/**", "user/login/**", "/403", "/css",
								"/images/**")
						.permitAll().requestMatchers("/registration", "/webjars/**").permitAll()
						.requestMatchers(HttpMethod.GET).permitAll().anyRequest().authenticated());
		// http.addFilterBefore(jwtrequesFilter,
		// UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	 BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	 UserDetailsService userDetailsService() {
		UserDetails adminDetails = User.builder().username("admin").password("password").roles("ADMIN").build();
		UserDetails user = User.builder().username("User").password("password").roles("USER").build();

		return new InMemoryUserDetailsManager(adminDetails, user);
	}

}
