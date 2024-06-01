package dev.arhimedes.calculator.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.arhimedes.calculator.http.enums.HttpEndPoints;
import dev.arhimedes.calculator.http.model.LoginErrorResponse;
import dev.arhimedes.calculator.http.model.LoginRequest;
import dev.arhimedes.calculator.http.model.LoginResponse;
import dev.arhimedes.calculator.http.model.NumberResponse;
import dev.arhimedes.calculator.http.repository.NumberResponseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HttpUtil {

    @Value("${client.id}")
    private String clientId;

    @Value("${client.secret}")
    private String clientSecret;

    @Value("${client.email}")
    private String clientEmail;

    @Value("${client.name}")
    private String clientName;

    @Value("${client.roll}")
    private String clientRoll;

    @Value("${client.company}")
    private String clientCompany;

    @Value("${logi.token}")
    private String token;

    private final HttpClient http;

    private final NumberResponseRepository repository;

    public LoginResponse login()  {
        LoginRequest request = new LoginRequest();
        request.setClientID(clientId);
        request.setClientSecret(clientSecret);
        request.setCompanyName(clientCompany);
        request.setRollNo(clientRoll);
        request.setOwnerEmail(clientEmail);
        request.setOwnerName(clientName);
        ObjectMapper mapper = new ObjectMapper();


        log.info(request.toString());


        try {
            System.out.println(mapper.writeValueAsString(request));
            var httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(HttpEndPoints.getUrl(HttpEndPoints.AUTH)))
                    .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(request)))
                    .build();


            var response = http.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.statusCode());

            if(response.statusCode() == 404){
                LoginErrorResponse errorResponse = mapper.readValue(response.body(), LoginErrorResponse.class);
                System.out.println(errorResponse.getMessage());
                return null;
            }

            log.info(response.body());

            return mapper.readValue(response.body(), LoginResponse.class);
        }catch (Exception e){
            return null;
        }
    }

    public NumberResponse getNumbers(HttpEndPoints endPoints){
        LoginResponse loginResponse = login();
        log.info("Token {}", token);
        try {
            var request = HttpRequest.newBuilder()
                    .uri(URI.create(HttpEndPoints.getUrl(endPoints)))
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .timeout(Duration.ofMillis(500))
                    .build();
            var response = http.send(request, HttpResponse.BodyHandlers.ofString());
            log.info(response.body());
            ObjectMapper mapper = new ObjectMapper();
            NumberResponse numberResponse = mapper.readValue(response.body(), NumberResponse.class);
            System.out.println(numberResponse.getNumber());
            numberResponse.setId(1);
            repository.save(numberResponse);
            return numberResponse;
        }catch (Exception e){
            log.info(e.getLocalizedMessage());
        }
        return new NumberResponse();
    }

}
