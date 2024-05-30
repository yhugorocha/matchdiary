package hugo.dev.matchdiary.repository;

import hugo.dev.matchdiary.model.Match;
import hugo.dev.matchdiary.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    @Query("SELECT m FROM Match m WHERE (m.teamOne = :team_one AND m.teamTwo = :team_two OR m.teamOne = :team_two AND m.teamTwo = :team_one) AND m.date = :date")
    Optional<Match> findByMatchForTeamOneTeamTwoDate(@Param("team_one") Team team_one, @Param("team_two") Team team_two, @Param("date") Date date);
}
