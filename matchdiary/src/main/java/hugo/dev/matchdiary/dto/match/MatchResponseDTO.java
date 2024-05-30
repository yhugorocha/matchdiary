package hugo.dev.matchdiary.dto.match;

import hugo.dev.matchdiary.dto.team.TeamDTO;
import hugo.dev.matchdiary.model.Match;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchResponseDTO {

    private Long id;
    private Date date;
    private TeamDTO teamOne;
    private Integer scoreTeamOne;
    private TeamDTO teamTwo;
    private Integer scoreTeamTwo;
    private TeamDTO team_supported;

    public MatchResponseDTO(Match match){
        this.id = match.getId();
        this.date = match.getDate();
        this.teamOne = new TeamDTO(match.getTeamOne());
        this.scoreTeamOne = match.getScoreTeamOne();
        this.teamTwo = new TeamDTO(match.getTeamTwo());
        this.scoreTeamTwo = match.getScoreTeamTwo();
        this.team_supported = new TeamDTO(match.getTeam_supported());
    }
}
