package com.example.springwsokhttpdemo.tempconvert;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/tempconvert")
@Validated
public class TempConvertController {

    private final TempConvertWebserviceClient soapClient;

    public TempConvertController(TempConvertWebserviceClient soapClient) {
        this.soapClient = soapClient;
    }

    @GetMapping(value = "/celsius-to-fahrenheit/{celsius}")
    public TempConvertResult celsiusToFahrenheit(@PathVariable @NotNull BigDecimal celsius) {
        BigDecimal fahrenheit = soapClient.celsiusToFahrenheit(celsius);
        return new TempConvertResult(celsius, fahrenheit);
    }

    @GetMapping("/fahrenheit-to-celsius/{fahrenheit}")
    public TempConvertResult fahrenheitToCelsius(@PathVariable @NotNull BigDecimal fahrenheit) {
        BigDecimal celsius = soapClient.fahrenheitToCelsius(fahrenheit);
        return new TempConvertResult(celsius, fahrenheit);
    }

    record TempConvertResult(BigDecimal celsius, BigDecimal fahrenheit) {
    }
}
