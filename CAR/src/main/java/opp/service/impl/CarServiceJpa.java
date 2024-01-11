package opp.service.impl;

import opp.dao.CarRepository;
import opp.domain.Car;
import opp.service.CarService;
import opp.service.EntityMissingException;
import opp.service.RequestDeniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceJpa implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public Optional<Car> findById(long carId) {
        return carRepository.findById(carId);
    }

    @Override
    public void createCar(Car car) {
        Assert.notNull(car,"Car object must be given");
        //Assert.notNull(car.getId(),"Car ID must not be null,not "+car.getId());
        carRepository.save(car);
    }

    @Override
    public void updateCar(Car car) {
        carRepository.save(car);

    }

    @Override
    public void deleteCar(long carId) {
        Car car = fetch(carId);
        carRepository.delete(car);
    }

    @Override
    public Car fetch(long carId) {
        return findById(carId).orElseThrow(
                ()-> new EntityMissingException(Car.class, carId)
        );
    }

    @Override
    public List<Car> listAll() {
        return carRepository.findAll();
    }

}
