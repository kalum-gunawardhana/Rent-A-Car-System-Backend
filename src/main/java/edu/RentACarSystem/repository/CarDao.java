package edu.RentACarSystem.repository;

import edu.RentACarSystem.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CarDao extends JpaRepository<CarEntity, Long> {
    List<CarEntity> findByAvailableTrue();
}