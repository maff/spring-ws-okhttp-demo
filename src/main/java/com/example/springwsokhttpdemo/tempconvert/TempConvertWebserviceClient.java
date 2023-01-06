package com.example.springwsokhttpdemo.tempconvert;

import com.w3schools.xml.CelsiusToFahrenheit;
import com.w3schools.xml.CelsiusToFahrenheitResponse;
import com.w3schools.xml.FahrenheitToCelsius;
import com.w3schools.xml.FahrenheitToCelsiusResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.math.BigDecimal;

public class TempConvertWebserviceClient extends WebServiceGatewaySupport {

    public BigDecimal celsiusToFahrenheit(BigDecimal celsius) {
        CelsiusToFahrenheit request = new CelsiusToFahrenheit();
        request.setCelsius(celsius.toPlainString());

        CelsiusToFahrenheitResponse response = (CelsiusToFahrenheitResponse) getWebServiceTemplate()
                .marshalSendAndReceive(
                        request,
                        new SoapActionCallback("https://www.w3schools.com/xml/CelsiusToFahrenheit")
                );

        return new BigDecimal(response.getCelsiusToFahrenheitResult());
    }

    public BigDecimal fahrenheitToCelsius(BigDecimal fahrenheit) {
        FahrenheitToCelsius request = new FahrenheitToCelsius();
        request.setFahrenheit(fahrenheit.toPlainString());

        FahrenheitToCelsiusResponse response = (FahrenheitToCelsiusResponse) getWebServiceTemplate()
                .marshalSendAndReceive(
                        request,
                        new SoapActionCallback("https://www.w3schools.com/xml/FahrenheitToCelsius")
                );

        return new BigDecimal(response.getFahrenheitToCelsiusResult());
    }
}
