package dev.arhimedes.calculator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

@Configuration
public class UtilityConfig {

    @Bean
    public HttpClient http(){
        return HttpClient.newHttpClient();
    }

}
