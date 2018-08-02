package com.mmna.chargers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.security.wss4j.Wss4jSecurityInterceptor;


@Configuration
public class EvChargersConfig {

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package in the <generatePackage> specified in
		// pom.xml
		marshaller.setContextPath("com.mmna.evchargers.wsdl");
		return marshaller;
	}
	
	@Bean
	  public WebServiceTemplate webServiceTemplate() {
	    WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
	    webServiceTemplate.setMarshaller(marshaller());
	    webServiceTemplate.setUnmarshaller(marshaller());
	    webServiceTemplate.setDefaultUri("https://webservices.chargepoint.com/webservices/chargepoint/services/5.0/");
	    webServiceTemplate.setInterceptors(new ClientInterceptor[]{ securityInterceptor() });
	    return webServiceTemplate;
	  }
	
    @Bean
    public Wss4jSecurityInterceptor securityInterceptor(){
    	String username = "[YOUR SOAP USERNAME]";
    	String pass = "[YOUR SOAP PASSWORD]";
    	Wss4jSecurityInterceptor security = new Wss4jSecurityInterceptor();
         
	    // Adds "Timestamp" and "UsernameToken" sections in SOAP header
	    security.setSecurementActions("UsernameToken");
	     
	    // Set values for "UsernameToken" sections in SOAP header
	    security.setSecurementPasswordType("PasswordText");
	    security.setSecurementUsername(username);
	    security.setSecurementPassword(pass);
	    return security;
    }
	


}
