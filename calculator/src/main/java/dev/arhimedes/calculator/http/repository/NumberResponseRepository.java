package dev.arhimedes.calculator.http.repository;

import dev.arhimedes.calculator.http.model.NumberResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NumberResponseRepository extends JpaRepository<NumberResponse, Integer> {
}
