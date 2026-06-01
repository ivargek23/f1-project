package hr.algebra.backendapi.service.impl;

import hr.algebra.backendapi.dto.DriverRequestDto;
import hr.algebra.backendapi.dto.DriverResponseDto;
import hr.algebra.backendapi.exception.ResourceNotFoundException;
import hr.algebra.backendapi.mapper.DriverMapper;
import hr.algebra.backendapi.model.Driver;
import hr.algebra.backendapi.model.Team;
import hr.algebra.backendapi.repository.DriverRepository;
import hr.algebra.backendapi.repository.TeamRepository;
import hr.algebra.backendapi.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final TeamRepository teamRepository;
    private final DriverMapper driverMapper;

    @Override
    public List<DriverResponseDto> getAllDrivers() {
        return driverRepository.findAll().stream().map(driverMapper::toResponseDto).toList();
    }

    @Override
    public Optional<DriverResponseDto> getDriverById(Long id) {
        return driverRepository.findById(id).map(driverMapper::toResponseDto);
    }

    @Override
    public DriverResponseDto createDriver(DriverRequestDto driverRequestDto) {
        Driver driver = driverMapper.toEntity(driverRequestDto, teamRepository.findById(driverRequestDto.getTeamId()).get());
        return driverMapper.toResponseDto(driverRepository.save(driver));
    }

    @Override
    public DriverResponseDto updateDriver(Long id, DriverRequestDto driverRequestDto) {
        Driver driverToUpdate = driverRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Driver with id " + id + " not found"));
        updateDriverData(driverRequestDto, driverToUpdate);
        return driverMapper.toResponseDto(driverRepository.save(driverToUpdate));

    }

    @Override
    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }

    private void updateDriverData(DriverRequestDto driverRequestDto, Driver driverToUpdate) {
        driverToUpdate.setCode(driverRequestDto.getCode());
        driverToUpdate.setDriverNumber(driverRequestDto.getDriverNumber());
        driverToUpdate.setImageUrl(driverRequestDto.getImageUrl());
        driverToUpdate.setFirstName(driverRequestDto.getFirstName());
        driverToUpdate.setLastName(driverRequestDto.getLastName());
        driverToUpdate.setDateOfBirth(driverRequestDto.getDateOfBirth());
        driverToUpdate.setNationality(driverRequestDto.getNationality());
        if (driverRequestDto.getTeamId() != null) {
            Optional<Team> team = teamRepository.findById(driverRequestDto.getTeamId());
            team.ifPresent(driverToUpdate::setTeam);
        }
    }
}
