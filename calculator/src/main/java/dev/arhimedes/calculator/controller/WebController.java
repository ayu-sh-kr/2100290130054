package dev.arhimedes.calculator.controller;

import dev.arhimedes.calculator.http.HttpUtil;
import dev.arhimedes.calculator.http.model.MyApiResponse;
import dev.arhimedes.calculator.http.model.NumberResponse;
import dev.arhimedes.calculator.http.repository.NumberResponseRepository;
import dev.arhimedes.calculator.utils.LimitedQueue;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static dev.arhimedes.calculator.http.enums.HttpEndPoints.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WebController {

    private final HttpUtil http;

    private final NumberResponseRepository repository;

    @GetMapping("/login")
    public void triggerLogin(){
        http.login();
    }

    @GetMapping("/number")
    public void getNumbers(){
        http.getNumbers(EVEN);
    }

    @GetMapping("/e")
    public MyApiResponse getEven(){
        return apiResponse(http.getNumbers(EVEN));
    }

    @GetMapping("/f")
    public MyApiResponse getFibo(){
        return apiResponse(http.getNumbers(FIBO));
    }

    @GetMapping("/r")
    public MyApiResponse getRand(){
        return apiResponse(http.getNumbers(RAND));
    }

    @GetMapping("/p")
    public MyApiResponse getPrime(){
        return apiResponse(http.getNumbers(PRIME));
    }

    private MyApiResponse apiResponse(NumberResponse curr){
        MyApiResponse myApiResponse = new MyApiResponse();
        LimitedQueue queue = new LimitedQueue(10);
        if(repository.existsById(1)){
            NumberResponse prev = repository.getReferenceById(1);
            myApiResponse.setWindowPrevState(prev.getNumber());

            for(Integer num: prev.getNumber()){
                queue.add(num);
            }
        }
        myApiResponse.setWindowCurrentState(curr.getNumber());

        for(Integer num: curr.getNumber()){
            queue.add(num);
        }

        double sum = 0.0;
        for (Integer num: queue){
            sum += num;
        }

        myApiResponse.setNumbers(queue.stream().toList());

        double avg = sum / queue.size();
        myApiResponse.setAvg(avg);
        return myApiResponse;
    }
}
