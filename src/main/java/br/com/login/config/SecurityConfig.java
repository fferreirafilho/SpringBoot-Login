package br.com.login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.login.exceptions.handler.CustomizedResponseEntityExcpetionHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private FilterToken filter;

	public SecurityConfig(FilterToken filter) {
		this.filter = filter;
	}

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeHttpRequests()

				.requestMatchers("/api/v1/auth/**").permitAll()
				.requestMatchers("/acess-denied/**").permitAll()
				.requestMatchers("/acess-denied").permitAll()

				.requestMatchers(HttpMethod.GET, "/api/v1/users/**").hasAnyAuthority("ROLE_PERSON", "ROLE_PERSON_READ")
				.requestMatchers(HttpMethod.POST, "/api/v1/users/**").hasAnyAuthority("ROLE_PERSON")
				.requestMatchers(HttpMethod.PUT, "/api/v1/users/**").hasAnyAuthority("ROLE_PERSON")
				.requestMatchers(HttpMethod.DELETE, "/api/v1/users/**").hasAnyAuthority("ROLE_PERSON")

				.requestMatchers(HttpMethod.GET, "/api/v1/groups/**").hasAnyAuthority("ROLE_GROUP", "ROLE_GROUP_READ")
				.requestMatchers(HttpMethod.POST, "/api/v1/groups/**").hasAnyAuthority("ROLE_GROUP")
				.requestMatchers(HttpMethod.PUT, "/api/v1/groups/**").hasAnyAuthority("ROLE_GROUP")
				.requestMatchers(HttpMethod.DELETE, "/api/v1/groups/**").hasAnyAuthority("ROLE_GROUP")

				.requestMatchers(HttpMethod.GET, "/api/v1/roles/**").hasAnyAuthority("ROLE_ROLES_READ")
				
				.anyRequest().denyAll()
				.and().exceptionHandling().accessDeniedHandler(new CustomizedResponseEntityExcpetionHandler()).and()
				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class).build();
	}
}