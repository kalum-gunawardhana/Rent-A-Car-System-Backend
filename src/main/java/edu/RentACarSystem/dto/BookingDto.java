package edu.RentACarSystem.dto;

import edu.RentACarSystem.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {
    private Long id;

    private Long customerId;

    private Long carId;

    private LocalDate startDate;

    private LocalDate endDate;

    private BookingStatus status;
}