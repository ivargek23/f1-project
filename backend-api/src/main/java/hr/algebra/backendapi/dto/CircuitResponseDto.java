package hr.algebra.backendapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CircuitResponseDto {
    private Long id;
    private String trackName;
    private String country;
    private String city;
    private Double circuitLength;
    private Integer firstGrandPrix;
    private Integer numberOfLaps;
    private Double raceDistance;
    private String fastestLapTime;
    private String circuitImageUrl;
}
