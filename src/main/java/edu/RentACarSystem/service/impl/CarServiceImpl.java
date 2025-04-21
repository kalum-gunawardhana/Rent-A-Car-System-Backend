package edu.RentACarSystem.service.impl;

import edu.RentACarSystem.entity.CarEntity;
import edu.RentACarSystem.repository.CarDao;
import edu.RentACarSystem.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarDao carRepository;

    @Override
    public CarEntity saveCar(CarEntity carEntity) {
        return carRepository.save(carEntity);
    }

    @Override
    public Optional<CarEntity> getCarById(Long id) {
        return carRepository.findById(id);
    }

    @Override
    public void deleteCarById(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public List<CarEntity> getAllAvailableCars() {
        return carRepository.findByAvailableTrue();
    }
}
