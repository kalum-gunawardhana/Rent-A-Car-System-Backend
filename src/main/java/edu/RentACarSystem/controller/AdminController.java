package edu.RentACarSystem.controller;

import edu.RentACarSystem.dto.BookingDto;
import edu.RentACarSystem.dto.CarDto;
import edu.RentACarSystem.entity.BookingEntity;
import edu.RentACarSystem.entity.CarEntity;
import edu.RentACarSystem.enums.BookingStatus;
import edu.RentACarSystem.service.BookingService;
import edu.RentACarSystem.service.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {
    @Autowired
    private CarService carService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private ModelMapper modelMapper;

    // Retrieve all bookings
    @GetMapping("/bookings")
    public ResponseEntity<List<BookingDto>> getAllBookings() {
        List<BookingEntity> bookings = bookingService.getAllBookings();
        List<BookingDto> bookingDtos = bookings.stream()
                .map(booking -> modelMapper.map(booking, BookingDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(bookingDtos);
    }

    // Update booking status
    @PutMapping("/bookings/{id}/status")
    public ResponseEntity<BookingDto> updateBookingStatus(@PathVariable Long id, @RequestParam BookingStatus status) {
        Optional<BookingEntity> updatedBooking = bookingService.updateBookingStatus(id, status);
        return updatedBooking
                .map(booking -> ResponseEntity.ok(modelMapper.map(booking, BookingDto.class)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new car
    @PostMapping("/cars")
    public ResponseEntity<CarDto> createCar(@RequestBody CarDto carDto) {
        CarEntity carEntity = modelMapper.map(carDto, CarEntity.class);
        CarEntity savedCar = carService.saveCar(carEntity);
        return ResponseEntity.ok(modelMapper.map(savedCar, CarDto.class));
    }

    // Update an existing car
    @PutMapping("/cars/{id}")
    public ResponseEntity<CarDto> updateCar(@PathVariable Long id, @RequestBody CarDto carDto) {
        Optional<CarEntity> existingCar = carService.getCarById(id);
        if (existingCar.isPresent()) {
            CarEntity carToUpdate = existingCar.get();
            carToUpdate.setBrand(carDto.getBrand());
            carToUpdate.setModel(carDto.getModel());
            carToUpdate.setPricePerDate(carDto.getPricePerDate());
            carToUpdate.setAvailable(carDto.getAvailable());
            CarEntity updatedCar = carService.saveCar(carToUpdate);
            return ResponseEntity.ok(modelMapper.map(updatedCar, CarDto.class));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a car
    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCarById(id);
        return ResponseEntity.noContent().build();
    }
}