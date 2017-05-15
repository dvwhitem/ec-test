package com.dv.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import java.util.Arrays;

@SpringBootApplication
@EnableOAuth2Client
public class RestClientApplication extends ResourceServerConfigurerAdapter {

	@Bean
	public OAuth2RestTemplate restTemplate() {
		DefaultOAuth2ClientContext context = new DefaultOAuth2ClientContext();
		return new OAuth2RestTemplate(resourceDetails(), context);
	}


	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.anyRequest().permitAll();
	}

	@Bean
	public ResourceOwnerPasswordResourceDetails resourceDetails() {
		ResourceOwnerPasswordResourceDetails details = new ResourceOwnerPasswordResourceDetails();
		details.setUsername("guest");
		details.setPassword("guest123");
		details.setClientId("phpclient");
		details.setClientSecret("phpclient123");
		details.setGrantType("password");
		details.setAccessTokenUri("https://spring-boot-rest-app-167308.appspot.com/oauth/token");
		details.setScope(Arrays.asList("read"));
		return details;
	}



	public static void main(String[] args) {
		SpringApplication.run(RestClientApplication.class, args);
	}
}
