package com.example.springwsokhttpdemo.tempconvert;

import com.example.springwsokhttpdemo.tempconvert.TempConvertConfigurationProperties.Authentication;
import com.example.springwsokhttpdemo.tempconvert.TempConvertConfigurationProperties.Logging;
import com.example.springwsokhttpdemo.tempconvert.TempConvertConfigurationProperties.Timeouts;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class TempConvertOkHttpClientConfiguration {

    @Bean
    @TempConvertOkHttpClient
    public OkHttpClient tempConvertOkHttpClient(OkHttpClient okHttpClient, TempConvertConfigurationProperties config) {
        OkHttpClient.Builder builder = okHttpClient.newBuilder(); // derive webservice client from generic one
        configureTimeouts(config.getTimeouts(), builder);
        configureAuthentication(config.getAuthentication(), builder);
        configureLogging(config.getLogging(), builder);

        return builder.build();
    }

    private void configureAuthentication(Authentication authentication, OkHttpClient.Builder builder) {
        if (authentication == null) {
            return;
        }

        // just an example - authorization via custom header
        builder.addInterceptor((chain -> {
            Request.Builder requestBuilder = chain.request().newBuilder();
            requestBuilder.header("X-Dummy-Access-Token", authentication.getAccessToken());

            return chain.proceed(requestBuilder.build());
        }));
    }

    private void configureLogging(Logging logging, OkHttpClient.Builder builder) {
        if (logging == null) {
            return;
        }

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(logging.getLevel());

        builder.addInterceptor(interceptor);
    }

    private void configureTimeouts(Timeouts timeouts, OkHttpClient.Builder builder) {
        if (timeouts == null) {
            return;
        }

        Optional.ofNullable(timeouts.getCallTimeout()).ifPresent(builder::callTimeout);
        Optional.ofNullable(timeouts.getConnectTimeout()).ifPresent(builder::connectTimeout);
        Optional.ofNullable(timeouts.getReadTimeout()).ifPresent(builder::readTimeout);
        Optional.ofNullable(timeouts.getWriteTimeout()).ifPresent(builder::writeTimeout);
    }
}
