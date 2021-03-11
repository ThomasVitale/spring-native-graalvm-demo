package com.thomasvitale.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

	@Bean
	SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) {
		return http
				.authorizeExchange(exchanges -> exchanges
						.pathMatchers("/").permitAll()
						.anyExchange().authenticated()
				)
				.httpBasic(withDefaults())
				.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public ReactiveUserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
		UserDetails user =
				User.builder()
						.username("sheldon")
						.password(passwordEncoder.encode("password"))
						.roles("USER")
						.build();

		return new MapReactiveUserDetailsService(user);
	}
}
