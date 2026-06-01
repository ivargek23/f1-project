package hr.algebra.backendapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverResponseDto {
    private Long id;
    private String code;
    private Integer driverNumber;
    private String imageUrl;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String nationality;
    private Long teamId;
    private String teamName;
}
