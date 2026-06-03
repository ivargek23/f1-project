package hr.algebra.backendapi.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverRequestDto {
    @NotBlank
    @Pattern(regexp = "^[A-Z]{3}$")
    private String code;

    @NotNull
    @Positive
    private Integer driverNumber;

    private String imageUrl;

    @NotBlank
    @Pattern(regexp = "^[\\p{L} .'-]{2,40}$")
    private String firstName;

    @NotBlank
    @Pattern(regexp = "^[\\p{L} .'-]{2,40}$")
    private String lastName;

    @Past
    private LocalDate dateOfBirth;

    @NotBlank
    @Pattern(regexp = "^[\\p{L} .'-]{2,40}$")
    private String nationality;

    @NotNull
    @Positive
    private Long teamId;
}
