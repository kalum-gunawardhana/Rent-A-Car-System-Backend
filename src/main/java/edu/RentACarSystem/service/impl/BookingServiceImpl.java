package edu.RentACarSystem.service.impl;

import edu.RentACarSystem.dto.BookingDto;
import edu.RentACarSystem.entity.BookingEntity;
import edu.RentACarSystem.entity.CarEntity;
import edu.RentACarSystem.entity.UserEntity;
import edu.RentACarSystem.enums.BookingStatus;
import edu.RentACarSystem.repository.BookingDao;
import edu.RentACarSystem.repository.CarDao;
import edu.RentACarSystem.repository.UserDao;
import edu.RentACarSystem.service.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingDao bookingDao;

    @Autowired
    private UserDao userRepository;

    @Autowired
    private CarDao carRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BookingDto createBooking(Long userId, Long carId, LocalDate start, LocalDate end) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        CarEntity car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        BookingEntity booking = new BookingEntity();
        booking.setCustomer(user);
        booking.setCar(car);
        booking.setStartDate(start);
        booking.setEndDate(end);
        booking.setStatus(BookingStatus.PENDING);

        BookingEntity savedBooking = bookingDao.save(booking);
        return modelMapper.map(savedBooking, BookingDto.class);
    }

    @Override
    public List<BookingDto> getBookingsByUser(Long userId) {
        List<BookingEntity> bookings = bookingDao.findByCustomerId(userId);
        return bookings.stream()
                .map(booking -> modelMapper.map(booking, BookingDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingEntity> getAllBookings() {
        return bookingDao.findAll();
    }

    @Override
    public Optional<BookingEntity> updateBookingStatus(Long id, BookingStatus status) {
        Optional<BookingEntity> bookingOpt = bookingDao.findById(id);
        if (bookingOpt.isPresent()) {
            BookingEntity booking = bookingOpt.get();
            booking.setStatus(status);
            bookingDao.save(booking);
        }
        return bookingOpt;
    }
}