package com.clonemovie.demo.configuration;

import com.siot.IamportRestClient.IamportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    String apiKey = "5618257762355013";
    String secretKey = "1SB7cQUixm4jkOUjTVvttqEcEpGTUjgE0f1D9UqrJ3QXr8seyZQxlCyVRRp9l4korlOLcqKB9VBGUqET";

    @Bean
    public IamportClient iamportClient() {
        return new IamportClient(apiKey, secretKey);
    }

}
