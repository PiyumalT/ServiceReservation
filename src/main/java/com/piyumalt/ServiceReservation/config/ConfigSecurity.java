package com.piyumalt.ServiceReservation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.net.URI;

@Configuration
@EnableWebSecurity
public class ConfigSecurity extends WebSecurityConfigurerAdapter {

    @Value("${client.post-logout-uri}")
    private String postLogoutUri;
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers(HttpMethod.GET,"/","/resources/img/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2Login()
                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessHandler(oidcLogoutSuccessHandler());
    }

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    private LogoutSuccessHandler oidcLogoutSuccessHandler() {

        OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler = new OidcClientInitiatedLogoutSuccessHandler(
                this.clientRegistrationRepository);
        oidcLogoutSuccessHandler.setPostLogoutRedirectUri(URI.create(postLogoutUri));
        return oidcLogoutSuccessHandler;
    }
}