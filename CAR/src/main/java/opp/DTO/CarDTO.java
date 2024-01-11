package opp.DTO;

import opp.domain.Car;

public class CarDTO {

    public Long id;

    public String manufacturer;

    public String model;

    public Long serialNumber;

    public CarDTO(Long id, String manufacturer, String model, Long serialNumber) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.serialNumber = serialNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public static CarDTO toDTO(Car car) {
        return new CarDTO(
                car.getId(),
                car.getManufacturer(),
                car.getModel(),
                car.getSerialNumber()
        );
    }
}
