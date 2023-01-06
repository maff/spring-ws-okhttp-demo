package com.example.springwsokhttpdemo.tempconvert;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Data
@Configuration
@ConfigurationProperties(prefix = "temp-convert")
@Validated
public class TempConvertConfigurationProperties {

    @NotBlank
    private String baseUrl;

}
