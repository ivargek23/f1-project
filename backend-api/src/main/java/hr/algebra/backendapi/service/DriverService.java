package hr.algebra.backendapi.service;

import hr.algebra.backendapi.dto.DriverRequestDto;
import hr.algebra.backendapi.dto.DriverResponseDto;

import java.util.List;

public interface DriverService {
    public List<DriverResponseDto> getAllDrivers();
    DriverResponseDto getDriverById(Long id);
    DriverResponseDto createDriver(DriverRequestDto driverRequestDto);
    DriverResponseDto updateDriver(Long id, DriverRequestDto driverRequestDto);
    void deleteDriver(Long id);
}
