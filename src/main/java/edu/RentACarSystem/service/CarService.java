package edu.RentACarSystem.service;

import edu.RentACarSystem.entity.CarEntity;

import java.util.List;
import java.util.Optional;

public interface CarService {

    CarEntity saveCar(CarEntity carEntity);

    Optional<CarEntity> getCarById(Long id);

    void deleteCarById(Long id);

    List<CarEntity> getAllAvailableCars();
}
