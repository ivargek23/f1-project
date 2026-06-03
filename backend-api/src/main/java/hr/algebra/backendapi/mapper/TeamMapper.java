package hr.algebra.backendapi.mapper;

import hr.algebra.backendapi.dto.TeamRequestDto;
import hr.algebra.backendapi.dto.TeamResponseDto;
import hr.algebra.backendapi.model.Team;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper {

    public Team toEntity(TeamRequestDto dto) {
        return Team.builder()
                .fullName(dto.getFullName())
                .shortName(dto.getShortName())
                .base(dto.getBase())
                .teamChief(dto.getTeamChief())
                .technicalChief(dto.getTechnicalChief())
                .powerUnit(dto.getPowerUnit())
                .firstTeamEntry(dto.getFirstTeamEntry())
                .teamLogoUrl(dto.getTeamLogoUrl())
                .build();
    }

    public TeamResponseDto toResponseDto(Team team) {
        return TeamResponseDto.builder()
                .id(team.getId())
                .fullName(team.getFullName())
                .shortName(team.getShortName())
                .base(team.getBase())
                .teamChief(team.getTeamChief())
                .technicalChief(team.getTechnicalChief())
                .powerUnit(team.getPowerUnit())
                .firstTeamEntry(team.getFirstTeamEntry())
                .teamLogoUrl(team.getTeamLogoUrl())
                .build();
    }
}
