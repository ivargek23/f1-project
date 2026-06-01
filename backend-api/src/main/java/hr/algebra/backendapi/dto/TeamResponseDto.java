package hr.algebra.backendapi.dto;

import lombok.Data;

@Data
public class TeamResponseDto {

    private Long id;
    private String fullName;
    private String shortName;
    private String base;
    private String teamChief;
    private String technicalChief;
    private String powerUnit;
    private Integer firstTeamEntry;
    private String teamLogoUrl;
}
