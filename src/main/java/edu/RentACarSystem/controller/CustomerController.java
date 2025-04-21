package edu.RentACarSystem.controller;

import edu.RentACarSystem.dto.BookingDto;
import edu.RentACarSystem.dto.CarDto;
import edu.RentACarSystem.entity.CarEntity;
import edu.RentACarSystem.service.BookingService;
import edu.RentACarSystem.service.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {
    @Autowired
    private CarService carService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private ModelMapper modelMapper;

    // Retrieve all available cars
    @GetMapping("/cars")
    public ResponseEntity<List<CarDto>> getAllAvailableCars() {
        List<CarEntity> cars = carService.getAllAvailableCars();
        List<CarDto> carDtos = cars.stream()
                .map(car -> modelMapper.map(car, CarDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(carDtos);
    }

    // Retrieve a car by ID
    @GetMapping("/cars/{id}")
    public ResponseEntity<CarDto> getCarById(@PathVariable Long id) {
        Optional<CarEntity> carEntityOptional = carService.getCarById(id);
        return carEntityOptional
                .map(car -> ResponseEntity.ok(modelMapper.map(car, CarDto.class)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new booking
    @PostMapping("/bookings")
    public ResponseEntity<BookingDto> createBooking(@RequestParam Long userId,
                                                    @RequestParam Long carId,
                                                    @RequestParam String startDate,
                                                    @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        BookingDto bookingDto = bookingService.createBooking(userId, carId, start, end);
        return ResponseEntity.ok(bookingDto);
    }

    // Retrieve bookings by user ID
    @GetMapping("/bookings/user/{userId}")
    public ResponseEntity<List<BookingDto>> getBookingsByUser(@PathVariable Long userId) {
        List<BookingDto> bookings = bookingService.getBookingsByUser(userId);
        List<BookingDto> bookingDtos = bookings.stream()
                .map(booking -> modelMapper.map(booking, BookingDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(bookingDtos);
    }
}