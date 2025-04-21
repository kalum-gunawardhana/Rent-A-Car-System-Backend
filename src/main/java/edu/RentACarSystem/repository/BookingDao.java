package edu.RentACarSystem.repository;

import edu.RentACarSystem.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookingDao extends JpaRepository<BookingEntity, Long> {
    List<BookingEntity> findByCustomerId(Long customerId);
}