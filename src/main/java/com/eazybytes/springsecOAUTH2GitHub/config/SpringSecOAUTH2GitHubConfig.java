package com.eazybytes.springsecOAUTH2GitHub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecOAUTH2GitHubConfig {
	
	private Environment environment;
	
	public SpringSecOAUTH2GitHubConfig(Environment environment)
	{
		this.environment = environment;
	}

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests)->requests.anyRequest().authenticated())
                .oauth2Login(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public ClientRegistrationRepository clientRepository() {
        ClientRegistration clientReg = clientRegistration();
        return new InMemoryClientRegistrationRepository(clientReg);
    }
    
    private ClientRegistration clientRegistration() {
    	
    	String googleclientId = environment.getProperty("spring.security.oauth2.client.registration.google.client-id");
    	String googleClientSecret = environment.getProperty("spring.security.oauth2.client.registration.google.client-secret");
    	
    	return CommonOAuth2Provider.GOOGLE.getBuilder("google").clientId(googleclientId)
 	           .clientSecret(googleClientSecret).build();
    	
	 }
    
    
    /*
    private ClientRegistration clientRegistration() {
		return CommonOAuth2Provider.GITHUB.getBuilder("github").clientId("8cf67ab304dc500092e3")
	           .clientSecret("6e6f91851c864684af2f91eaa08fb5041162768e").build();
	 }
	 */

}
