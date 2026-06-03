package hr.algebra.backendapi.repository;

import hr.algebra.backendapi.model.Driver;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<@NonNull Driver, @NonNull Long> {
    boolean existsByCode(String code);

    boolean existsByDriverNumber(Integer driverNumber);
}
