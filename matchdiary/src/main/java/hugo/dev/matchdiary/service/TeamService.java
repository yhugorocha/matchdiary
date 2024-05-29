package hugo.dev.matchdiary.service;

import hugo.dev.matchdiary.dto.team.TeamDTO;
import hugo.dev.matchdiary.exception.NotFoundException;
import hugo.dev.matchdiary.model.Team;
import hugo.dev.matchdiary.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;

    public List<TeamDTO> findAll() {
        return teamRepository.findAll().stream().map(team -> new TeamDTO(team)).collect(Collectors.toList());
    }

    public TeamDTO findById(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new NotFoundException("Team not found."));
        TeamDTO teamDTO = new TeamDTO(team);
        return teamDTO;
    }

    public Page<TeamDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return convertToDtoPage(teamRepository.findAll(pageable));
    }

    public Page<TeamDTO> convertToDtoPage(Page<Team> pageTeam){
        List<TeamDTO> listTeam = pageTeam.stream().map(team -> new TeamDTO(team)).collect(Collectors.toList());
        return new PageImpl<>(listTeam, PageRequest.of(pageTeam.getNumber(), pageTeam.getSize()), pageTeam.getTotalElements());
    }
}
