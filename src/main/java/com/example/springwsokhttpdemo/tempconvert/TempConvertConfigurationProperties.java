package com.example.springwsokhttpdemo.tempconvert;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;

@Data
@Configuration
@ConfigurationProperties(prefix = "temp-convert")
@Validated
public class TempConvertConfigurationProperties {

    @NotBlank
    private String baseUrl;

    @Valid
    private Authentication authentication;

    @Valid
    private Logging logging = new Logging();

    @Valid
    private Timeouts timeouts = new Timeouts();

    @Data
    static class Authentication {
        @NotBlank
        private String accessToken;
    }

    @Data
    static class Logging {
        private boolean enabled;

        @NotNull
        private Level level = Level.BASIC;
    }

    /**
     * See {@link okhttp3.OkHttpClient.Builder#callTimeout(Duration)} and other timeout methods for details.
     */
    @Data
    static class Timeouts {
        private Duration callTimeout;
        private Duration connectTimeout;
        private Duration readTimeout;
        private Duration writeTimeout;
    }
}
