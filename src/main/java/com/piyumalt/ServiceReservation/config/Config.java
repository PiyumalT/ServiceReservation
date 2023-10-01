package com.piyumalt.ServiceReservation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

import java.net.URI;

@Configuration
public class Config extends WebSecurityConfigurerAdapter {

	@Autowired
	ClientRegistrationRepository clientRegistrationRepository;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers("/","/resources/img/**").permitAll()
				.anyRequest().authenticated()
				.and().logout().logoutSuccessHandler(logoutSuccessHandler())
				.and()
				.oauth2Login();
	}

	OidcClientInitiatedLogoutSuccessHandler logoutSuccessHandler() {
		OidcClientInitiatedLogoutSuccessHandler handler = new OidcClientInitiatedLogoutSuccessHandler(
				clientRegistrationRepository);
		handler.setPostLogoutRedirectUri(URI.create("http://localhost:8080/"));
		return handler;
	}

}
