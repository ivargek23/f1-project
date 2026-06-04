package hr.algebra.backendapi.controller;

import hr.algebra.backendapi.dto.DriverRequestDto;
import hr.algebra.backendapi.dto.DriverResponseDto;
import hr.algebra.backendapi.service.DriverService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DriverController.class)
@AutoConfigureMockMvc(addFilters = false)
class DriverControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private DriverService driverService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllDrivers_shouldReturnAListOfDrivers() throws Exception {
        DriverResponseDto driver = createDriverResponse();

        when(driverService.getAllDrivers()).thenReturn(List.of(driver));

        mvc.perform(get("/api/v1/drivers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].code").value("VER"))
                .andExpect(jsonPath("$[0].driverNumber").value(1));

        verify(driverService).getAllDrivers();
    }

    @Test
    void getDriverById_shouldReturnADriver() throws Exception {
        DriverResponseDto driver = createDriverResponse();
        when(driverService.getDriverById(1L)).thenReturn(driver);
        mvc.perform(get("/api/v1/drivers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("VER"))
                .andExpect(jsonPath("$.driverNumber").value(1));
        verify(driverService).getDriverById(1L);
    }

    @Test
    void createDriver_shouldReturnADriver() throws Exception {
        DriverRequestDto dto = createDriverRequest();
        DriverResponseDto response = createDriverResponse();

        when(driverService.createDriver(any(DriverRequestDto.class))).thenReturn(response);

        mvc.perform(post("/api/v1/drivers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value("VER"))
                .andExpect(jsonPath("$.driverNumber").value(1));

        verify(driverService).createDriver(any(DriverRequestDto.class));
    }

    @Test
    void createDriver_whenDataIsNotValid_shouldReturnBadRequest() throws Exception {
        DriverRequestDto dto = createDriverRequest();
        dto.setCode(null);
        mvc.perform(post("/api/v1/drivers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
        verify(driverService, never()).createDriver(any(DriverRequestDto.class));
    }

    @Test
    void updateDriver_shouldReturnADriver() throws Exception {
        DriverRequestDto dto = createDriverRequest();
        DriverResponseDto response = createDriverResponse();

        when(driverService.updateDriver(eq(1L), any(DriverRequestDto.class))).thenReturn(response);

        mvc.perform(put("/api/v1/drivers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("VER"))
                .andExpect(jsonPath("$.driverNumber").value(1));

        verify(driverService).updateDriver(eq(1L), any(DriverRequestDto.class));
    }

    @Test
    void updateDriver_whenDataIsNotValid_shouldReturnBadRequest() throws Exception {
        DriverRequestDto dto = createDriverRequest();
        dto.setCode(null);
        mvc.perform(put("/api/v1/drivers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
        verify(driverService, never()).updateDriver(eq(1L), any(DriverRequestDto.class));
    }

    @Test
    void deleteDriver_shouldDeleteADriver() throws Exception {
        mvc.perform(delete("/api/v1/drivers/1"))
                .andExpect(status().isOk());
        verify(driverService).deleteDriver(1L);
    }

    private DriverRequestDto createDriverRequest() {
        DriverRequestDto driverRequestDto = new DriverRequestDto();
        driverRequestDto.setCode("VER");
        driverRequestDto.setFirstName("Max");
        driverRequestDto.setLastName("Verstappen");
        driverRequestDto.setDriverNumber(1);
        driverRequestDto.setNationality("Dutch");
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
        driverResponseDto.setTeamId(1L);
        driverResponseDto.setTeamName("Red Bull Racing");
        return driverResponseDto;
    }
}
