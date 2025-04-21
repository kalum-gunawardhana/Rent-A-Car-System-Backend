package edu.RentACarSystem.dto;

import edu.RentACarSystem.enums.CarBrand;
import edu.RentACarSystem.enums.CarModel;
import edu.RentACarSystem.enums.CarType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    private Long id;

    private CarBrand brand;

    private CarModel model;

    private Double pricePerDate;

    private Boolean available;

    private CarType type; // SUV, Sedan, etc.
}