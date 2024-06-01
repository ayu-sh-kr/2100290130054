package dev.arhimedes.calculator.http.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.jackson.JsonComponent;

import java.util.List;

@JsonComponent
@Getter @Setter
public class MyApiResponse {
    private List<Integer> numbers;
    private List<Integer> windowPrevState;
    private List<Integer> windowCurrentState;
    private double avg;
}
