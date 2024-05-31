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

    @Query("SELECT COUNT(*) FROM Match m WHERE m.teamOne = :team OR m.teamTwo =: team")
    Integer countMatchsByTeam(@Param("team") Team team);

    @Query("SELECT COUNT(*) FROM Match m WHERE m.teamOne = :team AND m.scoreTeamOne > m.scoreTeamTwo OR m.teamTwo = :team AND m.scoreTeamOne < m.scoreTeamTwo")
    Integer countWinsByTeam(@Param("team") Team team);

    @Query(value = "SELECT DATE_PART('day', NOW()::timestamp - MAX(date)::timestamp) FROM Match m WHERE m.team_one_id = :id OR m.team_two_id = :id", nativeQuery = true)
    Integer countDaysWithoutMatch(@Param("id") Long id);
}
