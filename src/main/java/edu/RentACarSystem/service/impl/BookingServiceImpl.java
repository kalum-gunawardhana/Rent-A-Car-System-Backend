package edu.RentACarSystem.service.impl;

import edu.RentACarSystem.entity.BookingEntity;
import edu.RentACarSystem.service.BookingService;

import java.time.LocalDate;
import java.util.List;

public class BookingServiceImpl implements BookingService {

    @Override
    public BookingEntity createBooking(Long userId, Long carId, LocalDate start, LocalDate end) {
        return null;
    }

    @Override
    public List<BookingEntity> getBookingsByUser(Long userId) {
        return List.of();
    }
}
