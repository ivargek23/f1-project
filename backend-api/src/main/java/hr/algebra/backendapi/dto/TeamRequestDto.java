package hr.algebra.backendapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamRequestDto {
    @NotBlank
    private String fullName;

    @NotBlank
    private String shortName;

    private String base;
    private String teamChief;
    private String technicalChief;
    private String powerUnit;
    private Integer firstTeamEntry;
    private String teamLogoUrl;
}
