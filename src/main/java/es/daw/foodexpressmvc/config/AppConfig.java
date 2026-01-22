package es.daw.foodexpressmvc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

    @Value("${api.api-url}")
    private String apiURL;

    @Value("${api.auth-url}")
    private String authURL;


    @Bean
    public WebClient webClientAPI(WebClient.Builder builder) {
        return builder
                .baseUrl(apiURL)
                .build();
    }

    @Bean
    public WebClient webClientAuth(WebClient.Builder builder) {
        return builder
                .baseUrl(authURL)
                .build();
    }

}
