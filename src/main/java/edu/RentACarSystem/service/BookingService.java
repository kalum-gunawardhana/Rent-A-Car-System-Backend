package edu.RentACarSystem.service;

import edu.RentACarSystem.dto.BookingDto;
import edu.RentACarSystem.entity.BookingEntity;
import edu.RentACarSystem.enums.BookingStatus;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookingService {
    BookingDto createBooking(Long userId, Long carId, LocalDate start, LocalDate end);

    List<BookingDto> getBookingsByUser(Long userId);

    List<BookingEntity> getAllBookings();

    Optional<BookingEntity> updateBookingStatus(Long id, BookingStatus status);
}