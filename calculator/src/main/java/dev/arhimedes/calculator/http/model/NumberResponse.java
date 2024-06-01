package dev.arhimedes.calculator.http.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
public class NumberResponse {

    @Id
    @JsonIgnore
    private Integer id;
    @ElementCollection
    @JsonProperty("numbers")
    private List<Integer> number = new ArrayList<>();
}
