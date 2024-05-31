package hugo.dev.matchdiary.service;


import hugo.dev.matchdiary.dto.report.ReportResponseDTO;
import hugo.dev.matchdiary.exception.NotFoundException;
import hugo.dev.matchdiary.model.Team;
import hugo.dev.matchdiary.repository.MatchRepository;
import hugo.dev.matchdiary.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReportService {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    MatchRepository matchRepository;


    public ReportResponseDTO getTeamReports(Long id){
        Team team = teamRepository.findById(id).orElseThrow(() -> new NotFoundException("Team not found"));

        Integer matchs = matchesQuantity(team);
        Integer wins = WinsQuantity(team);
        Double winPercentage = calculationWinPercentage(matchs, wins);
        Integer daysWithoutMatch = daysWithoutMatch(team.getId());

        ReportResponseDTO dto = new ReportResponseDTO();
        dto.setMatchesQuantity(matchs);
        dto.setWinsQuantity(wins);
        dto.setWinPercentage(winPercentage);
        dto.setDaysWithoutMatch(daysWithoutMatch);
        return dto;
    }

    private Integer matchesQuantity(Team team){
        return matchRepository.countMatchsByTeam(team);
    }

    private Integer WinsQuantity(Team team){
        return matchRepository.countWinsByTeam(team);
    }

    private Double calculationWinPercentage(Integer matchs, Integer wins){
       return Double.valueOf(100/matchs * wins);
    }

    private Integer daysWithoutMatch(Long id){
        return  matchRepository.countDaysWithoutMatch(id);
    }

}
