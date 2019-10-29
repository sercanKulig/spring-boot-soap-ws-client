package com.example.springbootsoapwsclient.api.config;

import com.example.springbootsoapwsclient.api.client.SoapClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;

@Configuration
public class SoapConfiguration {

    @Value("${client.username}")
    private String username;

    @Value("${client.password}")
    private String password;

    @Value("${client.default-uri}")
    private String uri;

    @Bean
    public Wss4jSecurityInterceptor securityInterceptor(){
        Wss4jSecurityInterceptor wss4jSecurityInterceptor = new Wss4jSecurityInterceptor();
        wss4jSecurityInterceptor.setSecurementActions("Timestamp UsernameToken");
        wss4jSecurityInterceptor.setSecurementUsername(username);
        wss4jSecurityInterceptor.setSecurementPassword(password);
        return wss4jSecurityInterceptor;
    }

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan("com.example.springbootsoapwsclient.api.user");
        return marshaller;
    }

    @Bean
    public SoapClient getSoapClient(){
        SoapClient soapClient = new SoapClient();
        soapClient.setMarshaller(marshaller());
        soapClient.setUnmarshaller(marshaller());
        soapClient.setDefaultUri(uri);
        ClientInterceptor[] interceptors = new ClientInterceptor[] {securityInterceptor()};
        soapClient.setInterceptors(interceptors);
        return soapClient;
    }
}
