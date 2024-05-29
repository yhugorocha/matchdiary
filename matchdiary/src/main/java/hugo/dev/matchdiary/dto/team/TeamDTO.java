package hugo.dev.matchdiary.dto.team;

import hugo.dev.matchdiary.model.Team;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamDTO {

    private Long id;

    @NotEmpty
    private String name;
    @NotEmpty
    private String state;
    @NotEmpty
    private String url_photo;

    public TeamDTO (Team team){
        this.id = team.getId();
        this.name = team.getName();
        this.state = team.getState();
        this.url_photo = team.getPhoto_url();
    }
}
