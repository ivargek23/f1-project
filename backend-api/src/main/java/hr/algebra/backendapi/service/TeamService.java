package hr.algebra.backendapi.service;

import hr.algebra.backendapi.dto.DriverResponseDto;
import hr.algebra.backendapi.dto.TeamRequestDto;
import hr.algebra.backendapi.dto.TeamResponseDto;

import java.util.List;

public interface TeamService {
    List<TeamResponseDto> getAllTeams();
    TeamResponseDto getTeamById(Long id);
    TeamResponseDto createTeam(TeamRequestDto teamRequestDto);
    TeamResponseDto updateTeam(Long id, TeamRequestDto teamRequestDto);
    void deleteTeam(Long id);
    List<DriverResponseDto> getDriversByTeamId(Long id);
}
