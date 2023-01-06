package com.example.springwsokhttpdemo.tempconvert;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.transport.http.OkHttpMessageSender;

@Configuration
public class TempConvertConfiguration {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan("com.w3schools.xml");
        return marshaller;
    }

    @Bean
    public OkHttpMessageSender tempConvertMessageSender(@TempConvertOkHttpClient OkHttpClient tempConvertOkHttpClient) {
        return new OkHttpMessageSender(tempConvertOkHttpClient);
    }

    @Bean
    public TempConvertWebserviceClient tempConvertWebserviceClient(
            TempConvertConfigurationProperties config,
            OkHttpMessageSender tempConvertMessageSender,
            Jaxb2Marshaller marshaller
    ) {
        TempConvertWebserviceClient client = new TempConvertWebserviceClient();
        client.setDefaultUri(config.getBaseUrl());
        client.setMessageSender(tempConvertMessageSender);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
