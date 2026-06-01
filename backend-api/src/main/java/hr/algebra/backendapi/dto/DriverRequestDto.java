package hr.algebra.backendapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverRequestDto {
    @NotBlank
    private String code;

    @NotNull
    @Positive
    private Integer driverNumber;
    private String imageUrl;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private LocalDate dateOfBirth;
    private String nationality;
    private Long teamId;
}
