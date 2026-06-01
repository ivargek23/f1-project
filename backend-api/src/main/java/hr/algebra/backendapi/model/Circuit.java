package hr.algebra.backendapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "circuits")
public class Circuit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "track_name")
    private String trackName;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "circuit_length")
    private Double circuitLength;

    @Column(name = "first_grand_prix")
    private Integer firstGrandPrix;

    @Column(name = "number_of_laps")
    private Integer numberOfLaps;

    @Column(name = "race_distance")
    private Double raceDistance;

    @Column(name = "fastest_lap_time")
    private String fastestLapTime;

    @Column(name = "circuit_image_url")
    private String circuitImageUrl;
}
