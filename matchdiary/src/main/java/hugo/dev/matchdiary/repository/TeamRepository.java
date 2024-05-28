package hugo.dev.matchdiary.repository;

import hugo.dev.matchdiary.model.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    Page<Team> findAll(Pageable pageable);
}
