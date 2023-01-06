package com.example.springwsokhttpdemo.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class OkHttpClientConfiguration {

    @Bean
    @Primary
    public OkHttpClient okHttpClient() {
        // application wide, generic okhttp client (could configure caching, default timeouts, tls, ...)
        return new OkHttpClient();
    }

}
