package hr.algebra.backendapi.mapper;

import hr.algebra.backendapi.dto.DriverRequestDto;
import hr.algebra.backendapi.dto.DriverResponseDto;
import hr.algebra.backendapi.model.Driver;
import hr.algebra.backendapi.model.Team;
import org.springframework.stereotype.Component;

@Component
public class DriverMapper {

    public Driver toEntity(DriverRequestDto dto, Team team) {
        return Driver.builder()
                .code(dto.getCode())
                .driverNumber(dto.getDriverNumber())
                .imageUrl(dto.getImageUrl())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .dateOfBirth(dto.getDateOfBirth())
                .nationality(dto.getNationality())
                .team(team)
                .build();
    }

    public DriverResponseDto toResponseDto(Driver driver) {
        return DriverResponseDto.builder()
                .id(driver.getId())
                .code(driver.getCode())
                .driverNumber(driver.getDriverNumber())
                .imageUrl(driver.getImageUrl())
                .firstName(driver.getFirstName())
                .lastName(driver.getLastName())
                .dateOfBirth(driver.getDateOfBirth())
                .nationality(driver.getNationality())
                .teamId(driver.getTeam().getId())
                .teamName(driver.getTeam().getFullName())
                .build();
    }
}
