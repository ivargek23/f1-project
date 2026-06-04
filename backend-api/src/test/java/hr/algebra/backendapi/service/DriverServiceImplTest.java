package hr.algebra.backendapi.service;

import hr.algebra.backendapi.dto.DriverRequestDto;
import hr.algebra.backendapi.dto.DriverResponseDto;
import hr.algebra.backendapi.exception.DuplicateResourceException;
import hr.algebra.backendapi.exception.ResourceNotFoundException;
import hr.algebra.backendapi.mapper.DriverMapper;
import hr.algebra.backendapi.model.Driver;
import hr.algebra.backendapi.model.Team;
import hr.algebra.backendapi.repository.DriverRepository;
import hr.algebra.backendapi.repository.TeamRepository;
import hr.algebra.backendapi.service.impl.DriverServiceImpl;
import hr.algebra.backendapi.utils.DriverValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DriverServiceImplTest {

    @Mock
    private DriverRepository driverRepository;

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private DriverValidator driverValidator;

    @Mock
    private DriverMapper driverMapper;

    @InjectMocks
    private DriverServiceImpl driverService;

    @Test
    void getAllDrivers_shouldReturnAListOfDrivers() {
        Driver driver = createDriver();
        DriverResponseDto driverResponseDto = createDriverResponse();

        when(driverRepository.findAll()).thenReturn(List.of(driver));
        when(driverMapper.toResponseDto(driver)).thenReturn(driverResponseDto);

        List<DriverResponseDto> drivers = driverService.getAllDrivers();

        assertEquals(1, drivers.size());
        assertEquals("VER", drivers.getFirst().getCode());

        verify(driverRepository).findAll();
        verify(driverMapper).toResponseDto(driver);
    }

    @Test
    void getDriverById_whenDriverExists_shouldReturnADriver() {
        Driver driver = createDriver();
        DriverResponseDto driverResponseDto = createDriverResponse();

        when(driverRepository.findById(1L)).thenReturn(java.util.Optional.of(driver));
        when(driverMapper.toResponseDto(driver)).thenReturn(driverResponseDto);

        DriverResponseDto driverDto = driverService.getDriverById(1L);

        assertEquals("VER", driverDto.getCode());
        verify(driverRepository).findById(1L);
        verify(driverMapper).toResponseDto(driver);
    }

    @Test
    void getDriverById_whenDriverDoesNotExist_shouldThrowException() {
        when(driverRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> driverService.getDriverById(1L));

        verify(driverRepository).findById(1L);
    }

    @Test
    void createDriver_shouldReturnADriver() {
        DriverRequestDto request = createDriverRequest();
        Driver driver = createDriver();
        Driver savedDriver = createDriver();
        Team team = createTeam();
        DriverResponseDto driverResponseDto = createDriverResponse();

        doNothing().when(driverValidator).validateCreateDriverData(request);
        when(driverMapper.toEntity(request, team)).thenReturn(driver);
        when(driverMapper.toResponseDto(savedDriver)).thenReturn(driverResponseDto);
        when(driverRepository.save(any(Driver.class))).thenReturn(savedDriver);
        when(teamRepository.findById(1L)).thenReturn(java.util.Optional.of(team));

        DriverResponseDto response = driverService.createDriver(request);

        assertEquals("VER", response.getCode());
        assertEquals(request.getDriverNumber(), response.getDriverNumber());

        verify(driverValidator).validateCreateDriverData(request);
        verify(driverRepository).save(any(Driver.class));
    }

    @Test
    void createDriver_shouldThrowExceptionWhenCodeAlreadyExists() {
        DriverRequestDto request = createDriverRequest();

        doThrow(new DuplicateResourceException("Code", "VER")).when(driverValidator).validateCreateDriverData(request);

        assertThrows(DuplicateResourceException.class, () -> driverService.createDriver(request));
    }

    @Test
    void createDriver_shouldThrowExceptionWhenDriverNumberAlreadyExists() {
        DriverRequestDto request = createDriverRequest();

        doThrow(new DuplicateResourceException("Driver number", 1)).when(driverValidator).validateCreateDriverData(request);

        assertThrows(DuplicateResourceException.class, () -> driverService.createDriver(request));
    }

    @Test
    void updateDriver_shouldReturnADriver() {
        DriverRequestDto request = createDriverRequest();
        Driver driver = createDriver();
        DriverResponseDto driverResponseDto = createDriverResponse();

        when(driverRepository.findById(1L)).thenReturn(java.util.Optional.of(driver));
        when(driverMapper.toResponseDto(driver)).thenReturn(driverResponseDto);
        when(driverRepository.save(any(Driver.class))).thenReturn(driver);

        DriverResponseDto response = driverService.updateDriver(1L, request);

        assertEquals("VER", response.getCode());
        assertEquals(request.getDriverNumber(), response.getDriverNumber());
    }

    @Test
    void deleteDriver_shouldDeleteADriver() {
        when(driverRepository.existsById(1L)).thenReturn(true);

        driverService.deleteDriver(1L);

        verify(driverRepository).deleteById(1L);
    }

    @Test
    void deleteDriver_whenDriverDoesNotExist_shouldThrowException() {
        when(driverRepository.existsById(1L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> driverService.deleteDriver(1L));
    }

    private DriverRequestDto createDriverRequest() {
        DriverRequestDto driverRequestDto = new DriverRequestDto();
        driverRequestDto.setCode("VER");
        driverRequestDto.setFirstName("Max");
        driverRequestDto.setLastName("Verstappen");
        driverRequestDto.setDriverNumber(1);
        driverRequestDto.setNationality("Dutch");
        driverRequestDto.setImageUrl("/images/max.jpg");
        driverRequestDto.setDateOfBirth(LocalDate.of(1997, 9, 30));
        driverRequestDto.setTeamId(1L);

        return driverRequestDto;
    }

    private DriverResponseDto createDriverResponse() {
        DriverResponseDto driverResponseDto = new DriverResponseDto();
        driverResponseDto.setId(1L);
        driverResponseDto.setCode("VER");
        driverResponseDto.setFirstName("Max");
        driverResponseDto.setLastName("Verstappen");
        driverResponseDto.setDriverNumber(1);
        driverResponseDto.setNationality("Dutch");
        driverResponseDto.setImageUrl("/images/max.jpg");
        driverResponseDto.setDateOfBirth(LocalDate.of(1997, 9, 30));
        driverResponseDto.setTeamId(1L);

        return driverResponseDto;
    }

    private Driver createDriver() {
        Driver driver = new Driver();
        driver.setCode("VER");
        driver.setFirstName("Max");
        driver.setLastName("Verstappen");
        driver.setId(1L);
        driver.setDriverNumber(1);
        driver.setNationality("Dutch");
        driver.setImageUrl("/images/max.jpg");
        driver.setDateOfBirth(LocalDate.of(1997, 9, 30));
        Team team = createTeam();
        driver.setTeam(team);
        return driver;
    }

    private Team createTeam() {
        Team team = new Team();
        team.setId(1L);
        team.setFullName("Red Bull Racing");
        team.setShortName("RBR");
        team.setBase("Red Bull");
        team.setTeamChief("Laurent Mekies");
        team.setTechnicalChief("GP");
        team.setPowerUnit("Red Bull");
        team.setFirstTeamEntry(1997);
        team.setTeamLogoUrl("/images/rbr.jpg");

        return team;
    }
}
