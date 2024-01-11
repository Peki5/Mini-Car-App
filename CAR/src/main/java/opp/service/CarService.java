package opp.service;

import opp.domain.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {
    Optional<Car> findById(long carId);

    void createCar(Car car);

    void updateCar(Car car);

    void deleteCar(long bookId);

    Car fetch(long bookId);

    List<Car> listAll();

}