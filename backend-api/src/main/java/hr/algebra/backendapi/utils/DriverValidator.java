package hr.algebra.backendapi.utils;

import hr.algebra.backendapi.dto.DriverRequestDto;
import hr.algebra.backendapi.exception.DuplicateResourceException;
import hr.algebra.backendapi.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DriverValidator {
    private final DriverRepository driverRepository;

    public void validateCreateDriverData(DriverRequestDto driverRequestDto) {
        if (driverRepository.existsByCode(driverRequestDto.getCode())) {
            throw new DuplicateResourceException("Code", driverRequestDto.getCode());
        }
        if (driverRepository.existsByDriverNumber(driverRequestDto.getDriverNumber())) {
            throw new DuplicateResourceException("Driver number", driverRequestDto.getDriverNumber());
        }
    }
}
