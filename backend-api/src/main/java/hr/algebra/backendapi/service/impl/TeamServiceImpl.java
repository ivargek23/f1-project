package hr.algebra.backendapi.service.impl;

import hr.algebra.backendapi.dto.DriverResponseDto;
import hr.algebra.backendapi.dto.TeamRequestDto;
import hr.algebra.backendapi.dto.TeamResponseDto;
import hr.algebra.backendapi.exception.ResourceNotFoundException;
import hr.algebra.backendapi.mapper.TeamMapper;
import hr.algebra.backendapi.model.Team;
import hr.algebra.backendapi.repository.TeamRepository;
import hr.algebra.backendapi.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    @Override
    public List<TeamResponseDto> getAllTeams() {
        return teamRepository.findAll().stream().map(teamMapper::toResponseDto).toList();
    }

    @Override
    public TeamResponseDto getTeamById(Long id) {
        return teamRepository.findById(id).map(teamMapper::toResponseDto).orElseThrow(() -> new ResourceNotFoundException("Team with id " + id + " not found"));
    }

    @Override
    public TeamResponseDto createTeam(TeamRequestDto teamRequestDto) {

        Team team = teamMapper.toEntity(teamRequestDto);
        return teamMapper.toResponseDto(teamRepository.save(team));
    }

    @Override
    public TeamResponseDto updateTeam(Long id, TeamRequestDto teamRequestDto) {

        Team team = teamRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Team with id " + id + " not found"));
        setTeamData(teamRequestDto, team);

        return teamMapper.toResponseDto(teamRepository.save(team));
    }

    private static void setTeamData(TeamRequestDto teamRequestDto, Team team) {
        team.setFullName(teamRequestDto.getFullName());
        team.setShortName(teamRequestDto.getShortName());
        team.setBase(teamRequestDto.getBase());
        team.setTeamChief(teamRequestDto.getTeamChief());
        team.setTechnicalChief(teamRequestDto.getTechnicalChief());
        team.setPowerUnit(teamRequestDto.getPowerUnit());
        team.setFirstTeamEntry(teamRequestDto.getFirstTeamEntry());
        team.setTeamLogoUrl(teamRequestDto.getTeamLogoUrl());
    }

    @Override
    public void deleteTeam(Long id) {
        if (!teamRepository.existsById(id)) {
            throw new ResourceNotFoundException("Team with id " + id + " not found");
        }
        teamRepository.deleteById(id);
    }

    @Override
    public List<DriverResponseDto> getDriversByTeamId(Long id) {

        return null;
    }
}
