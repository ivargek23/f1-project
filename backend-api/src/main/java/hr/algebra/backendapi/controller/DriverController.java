package hr.algebra.backendapi.controller;

import hr.algebra.backendapi.dto.DriverRequestDto;
import hr.algebra.backendapi.dto.DriverResponseDto;
import hr.algebra.backendapi.exception.ResourceNotFoundException;
import hr.algebra.backendapi.service.DriverService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @GetMapping
    public ResponseEntity<@NonNull List<DriverResponseDto>> getAllDrivers() {
        return ResponseEntity.ok(driverService.getAllDrivers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<@NonNull DriverResponseDto> getDriverById(@NotNull @PathVariable Long id) {
        return ResponseEntity.ok(driverService.getDriverById(id).orElseThrow(() -> new ResourceNotFoundException("Driver with id " + id + " not found")));
    }

    @PostMapping
    public ResponseEntity<@NonNull DriverResponseDto> createDriver(@RequestBody @NonNull @Valid DriverRequestDto driverRequestDto) {
        DriverResponseDto driverResponseDto = driverService.createDriver(driverRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(driverResponseDto);
    }
}
