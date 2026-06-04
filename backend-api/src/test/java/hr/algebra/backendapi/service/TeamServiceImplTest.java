package hr.algebra.backendapi.service;

import hr.algebra.backendapi.dto.TeamRequestDto;
import hr.algebra.backendapi.dto.TeamResponseDto;
import hr.algebra.backendapi.exception.ResourceNotFoundException;
import hr.algebra.backendapi.mapper.TeamMapper;
import hr.algebra.backendapi.model.Team;
import hr.algebra.backendapi.repository.TeamRepository;
import hr.algebra.backendapi.service.impl.TeamServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeamServiceImplTest {
    @Mock
    private TeamRepository teamRepository;

    @Mock
    private TeamMapper teamMapper;

    @InjectMocks
    private TeamServiceImpl teamService;

    @Test
    void getAllTeams_shouldReturnAListOfTeams() {
        Team team = createTeam();
        TeamResponseDto dto = createTeamResponseDto();

        when(teamRepository.findAll()).thenReturn(List.of(team));
        when(teamMapper.toResponseDto(team)).thenReturn(dto);

        List<TeamResponseDto> teams = teamService.getAllTeams();

        assertEquals("RBR", teams.getFirst().getShortName());
        assertEquals(1, teams.size());

        verify(teamRepository).findAll();
        verify(teamMapper).toResponseDto(team);
    }

    @Test
    void getTeamById_whenExists_shouldReturnTeam() {
        Team team = createTeam();
        TeamResponseDto dto = createTeamResponseDto();

        when(teamRepository.findById(1L)).thenReturn(Optional.of(team));
        when(teamMapper.toResponseDto(team)).thenReturn(dto);

        TeamResponseDto teamDto = teamService.getTeamById(1L);
        assertEquals("RBR", teamDto.getShortName());
        verify(teamRepository).findById(1L);
        verify(teamMapper).toResponseDto(team);
    }

    @Test
    void getTeamById_whenDoesNotExist_shouldThrowException() {
        when(teamRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> teamService.getTeamById(1L));

        verify(teamRepository).findById(1L);
    }

    @Test
    void createTeam_shouldReturnATeam() {
        Team team = createTeam();
        TeamResponseDto dto = createTeamResponseDto();
        TeamRequestDto request = createTeamRequest();
        Team savedTeam = createTeam();

        when(teamMapper.toResponseDto(team)).thenReturn(dto);
        when(teamMapper.toEntity(request)).thenReturn(team);
        when(teamRepository.save(any(Team.class))).thenReturn(savedTeam);

        TeamResponseDto response = teamService.createTeam(request);

        assertEquals("RBR", response.getShortName());
        assertEquals(1, response.getId());

        verify(teamRepository).save(team);
    }

    @Test
    void updateTeam_shouldReturnATeamWithUpdatedData() {
        Team team = createTeam();
        TeamRequestDto request = createTeamRequest();
        TeamResponseDto dto = createTeamResponseDto();
        Team savedTeam = createTeam();

        when(teamMapper.toResponseDto(savedTeam)).thenReturn(dto);
        when(teamRepository.findById(1L)).thenReturn(Optional.of(team));
        when(teamRepository.save(any(Team.class))).thenReturn(savedTeam);

        TeamResponseDto response = teamService.updateTeam(1L, request);

        assertEquals("RBR", response.getShortName());
        assertEquals(1, response.getId());
        verify(teamRepository).findById(1L);
        verify(teamRepository).save(team);
    }

    @Test
    void updateTeam_whenTeamDoesNotExist_shouldThrowException() {
        TeamRequestDto request = createTeamRequest();

        when(teamRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> teamService.updateTeam(1L, request));
    }

    @Test
    void deleteTeam_shouldDeleteATeam() {
        when(teamRepository.existsById(1L)).thenReturn(true);

        teamService.deleteTeam(1L);

        verify(teamRepository).deleteById(1L);
    }

    @Test
    void deleteTeam_whenTeamDoesNotExist_shouldThrowException() {
        when(teamRepository.existsById(1L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> teamService.deleteTeam(1L));
        verify(teamRepository).existsById(1L);
    }

    private TeamRequestDto createTeamRequest() {
        TeamRequestDto dto = new TeamRequestDto();
        dto.setFullName("Red Bull Racing");
        dto.setShortName("RBR");
        dto.setBase("Red Bull");
        dto.setTeamChief("Laurent Mekies");
        dto.setTechnicalChief("GP");
        dto.setPowerUnit("Red Bull");
        dto.setFirstTeamEntry(1997);
        dto.setTeamLogoUrl("/images/rbr.jpg");

        return dto;
    }

    private TeamResponseDto createTeamResponseDto() {
        TeamResponseDto dto = new TeamResponseDto();
        dto.setId(1L);
        dto.setFullName("Red Bull Racing");
        dto.setShortName("RBR");
        dto.setBase("Red Bull");
        dto.setTeamChief("Laurent Mekies");
        dto.setTechnicalChief("GP");
        dto.setPowerUnit("Red Bull");
        dto.setFirstTeamEntry(1997);
        dto.setTeamLogoUrl("/images/rbr.jpg");

        return dto;
    }

    private Team createTeam() {
        Team team = new Team();
        team.setId(1L);
        team.setFullName("Red Bull Racing");
        team.setShortName("RBR");
        team.setBase("Red Bull");
        team.setTeamChief("Laurent Mekies");
        team.setTechnicalChief("GP");
        team.setPowerUnit("Red Bull");
        team.setFirstTeamEntry(1997);
        team.setTeamLogoUrl("/images/rbr.jpg");

        return team;
    }
}
