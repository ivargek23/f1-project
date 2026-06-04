package hr.algebra.backendapi.controller;

import hr.algebra.backendapi.dto.TeamResponseDto;
import hr.algebra.backendapi.model.Team;
import hr.algebra.backendapi.service.TeamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TeamController.class)
@AutoConfigureMockMvc(addFilters = false)
public class TeamControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private TeamService teamService;

    @Test
    void getAllTeams_shouldReturnAListOfTeams() throws Exception {
        TeamResponseDto dto = createTeamResponseDto();

        when(teamService.getAllTeams()).thenReturn(List.of(dto));

        mvc.perform(get("/api/v1/teams"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].fullName").value("Red Bull Racing"))
                .andExpect(jsonPath("$[0].shortName").value("RBR"));

        verify(teamService).getAllTeams();
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
