package hr.algebra.backendapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
public class CircuitRequestDto {
    @NotBlank(message = "Track name must not be blank.")
    @Length(min = 2, max = 40, message = "Track name must be between 2 and 40 characters.")
    private String trackName;

    @NotBlank(message = "Country must not be blank.")
    private String country;

    @NotBlank(message = "City must not be blank.")
    @Length(min = 2, max = 40, message = "City must be between 2 and 40 characters.")
    private String city;
    private Double circuitLength;
    private Integer firstGrandPrix;
    private Integer numberOfLaps;
    private Double raceDistance;
    private String fastestLapTime;
    private String circuitImageUrl;
}
