package opp.rest;

import opp.DTO.CarDTO;
import opp.domain.Car;
import opp.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("")
public class CarController {
    @Autowired
    private CarService carService;

    @PostMapping(value = "/add")
    public ResponseEntity<List<CarDTO>> createCar(@RequestBody CarDTO carDTO){
        Optional<Car> existingCar = carService.findById(carDTO.getId());
        if(existingCar.isEmpty()){
            Car car1 = new Car(carDTO.getId(),carDTO.getManufacturer(), carDTO.getModel(), carDTO.serialNumber);
            carService.createCar(car1);

            List<CarDTO> carDTOs = carService.listAll().stream()
                    .map(CarDTO::toDTO) // Assuming you have a toDTO method in BookDTO
                    .collect(Collectors.toList());

            return new ResponseEntity<>(carDTOs, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/car/{id}")
    public ResponseEntity<Car> showCar(@PathVariable Long id) {
        Optional<Car> carDto;
        try {
            carDto = carService.findById(id);
            if (carDto.isPresent()) {
                return ResponseEntity.ok(carDto.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping(value = "/remove/{id}")
    public ResponseEntity<List<CarDTO>> deleteCar(@PathVariable Long id) {
        Optional<Car> carDTO = carService.findById(id);
        if(carDTO.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        carService.deleteCar(id);
        List<CarDTO> carDTOs = carService.listAll().stream()
                .map(CarDTO::toDTO) // Assuming you have a toDTO method in BookDTO
                .collect(Collectors.toList());
        return new ResponseEntity<>(carDTOs, HttpStatus.OK);
    }

    @PostMapping(value = "/modify")
    public ResponseEntity<List<CarDTO>> modifyCar(@RequestBody CarDTO carDTO) {
        Optional<Car> existingCarOptional = carService.findById(carDTO.getId());

        if (existingCarOptional.isPresent()) {
            Car existingCar = (Car) existingCarOptional.get();

            existingCar.setSerialNumber(carDTO.getSerialNumber());
            existingCar.setManufacturer(carDTO.getManufacturer());
            existingCar.setModel(carDTO.getModel());

            carService.updateCar(existingCar);

            List<CarDTO> carDTOs = carService.listAll().stream()
                    .map(CarDTO::toDTO)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(carDTOs, HttpStatus.OK);
        }else{
            Car car1 = new Car(carDTO.getId(),carDTO.getManufacturer(), carDTO.getModel(), carDTO.serialNumber);
            carService.createCar(car1);

            List<CarDTO> carDTOs = carService.listAll().stream()
                    .map(CarDTO::toDTO)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(carDTOs, HttpStatus.OK);
        }
    }



}
