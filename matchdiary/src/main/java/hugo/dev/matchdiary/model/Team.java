package hugo.dev.matchdiary.model;

import hugo.dev.matchdiary.dto.team.TeamDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotEmpty
    @Column(name = "name")
    private String name;

    @NotEmpty
    @Column(name = "state")
    private String state;

    @NotEmpty
    @Column(name = "photo_url", nullable = false)
    private String photo_url;

    public Team(TeamDTO teamDTO){
        this.id = teamDTO.getId();
        this.name = teamDTO.getName();
        this.state = teamDTO.getState();
        this.photo_url = teamDTO.getPhoto_url();
    }
}
