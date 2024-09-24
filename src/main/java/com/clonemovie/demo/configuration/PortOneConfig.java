package com.clonemovie.demo.configuration;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "iamport")
public class PortOneConfig {
    @Value("${iamport.imp.code}")
    private String impCode;
    private String apiKey;
    private String secretKey;

    public String getImpCode() {
        return impCode;
    }
    public void setImpCode(String impCode) {
        this.impCode = impCode;
    }

    public String getApiKey() {
        return apiKey;
    }
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }


}
