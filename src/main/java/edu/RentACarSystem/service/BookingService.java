package edu.RentACarSystem.service;

import edu.RentACarSystem.entity.BookingEntity;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    BookingEntity createBooking(Long userId, Long carId, LocalDate start, LocalDate end);

    List<BookingEntity> getBookingsByUser(Long userId);
}
