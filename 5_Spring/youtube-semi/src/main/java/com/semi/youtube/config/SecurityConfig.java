package com.semi.youtube.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(csrf -> csrf.disable())
				.formLogin(login ->
					login.loginPage("/login") // 로그인 페이지 등록
					.defaultSuccessUrl("/", true) // 로그인 성공했을때
					.permitAll()
				)
				.logout(logout ->
					logout.logoutUrl("logout") // 로그아웃 요청 url
					.logoutSuccessUrl("/") // 로그아웃 성공했을때
					.permitAll()
				)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
				.authorizeHttpRequests(authorize ->
						authorize
						.requestMatchers("/like", "/unlike").authenticated()
						.anyRequest().permitAll())
				.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
