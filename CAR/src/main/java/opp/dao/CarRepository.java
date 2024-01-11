package opp.dao;

import opp.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car,Long> {
    Optional<Object> findByserialNumber(Long serialNumber);
}
