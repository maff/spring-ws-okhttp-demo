package com.example.springwsokhttpdemo.tempconvert;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class TempConvertConfiguration {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan("com.w3schools.xml");
        return marshaller;
    }

    @Bean
    public TempConvertWebserviceClient tempConvertWebserviceClient(
            TempConvertConfigurationProperties config,
            Jaxb2Marshaller marshaller
    ) {
        TempConvertWebserviceClient client = new TempConvertWebserviceClient();
        client.setDefaultUri(config.getBaseUrl());
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
