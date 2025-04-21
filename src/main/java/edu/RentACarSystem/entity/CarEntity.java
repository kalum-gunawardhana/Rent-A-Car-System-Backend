package edu.RentACarSystem.entity;

import edu.RentACarSystem.enums.CarBrand;
import edu.RentACarSystem.enums.CarModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CarBrand brand;

    @Enumerated(EnumType.STRING)
    private CarModel model;

    private Double pricePerDate;

    private Boolean available;
}