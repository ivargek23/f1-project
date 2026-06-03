package hr.algebra.backendapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "teams")
@Builder
@AllArgsConstructor
public class Team {

    protected Team() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, name = "full_name", nullable = false)
    private String fullName;

    @Column(unique = true, name = "short_name")
    private String shortName;

    @Column(name = "base")
    private String base;

    @Column(name = "team_chief")
    private String teamChief;

    @Column(name = "technical_chief")
    private String technicalChief;

    @Column(name = "power_unit")
    private String powerUnit;

    @Column(name = "first_team_entry")
    private Integer firstTeamEntry;

    @Column(name = "team_logo_url")
    private String teamLogoUrl;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Driver> drivers = new ArrayList<>();
}
