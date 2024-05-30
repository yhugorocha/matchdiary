package hugo.dev.matchdiary.service;

import hugo.dev.matchdiary.dto.match.MatchRequestDTO;
import hugo.dev.matchdiary.dto.match.MatchResponseDTO;
import hugo.dev.matchdiary.dto.team.TeamDTO;
import hugo.dev.matchdiary.exception.BusinessRuleException;
import hugo.dev.matchdiary.exception.NotFoundException;
import hugo.dev.matchdiary.model.Match;
import hugo.dev.matchdiary.model.Team;
import hugo.dev.matchdiary.repository.MatchRepository;
import hugo.dev.matchdiary.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatchService {

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    TeamRepository teamRepository;

    public List<MatchResponseDTO> getAllMatchs(){
        return matchRepository.findAll().stream().map(match -> new MatchResponseDTO(match)).collect(Collectors.toList());
    }

    public MatchResponseDTO save(MatchRequestDTO matchRequestDTO) {
        Match match = validatedMatch(matchRequestDTO);
        return new MatchResponseDTO(matchRepository.save(match));
    }

    /*
    * 1 - Faz a verificação se os times existem no banco,
    * 2 - Se já existe uma partida cadastrada com os 2 times na data.
    * 3 - Se verifica se o teamSupported e algum dos 2 times informados.
     */
    public Match validatedMatch(MatchRequestDTO matchRequestDTO){
        Team teamOne = teamRepository.findById(matchRequestDTO.getTeamOne()).orElseThrow(()-> new NotFoundException("Team One Not Found"));
        Team teamTwo = teamRepository.findById(matchRequestDTO.getTeamTwo()).orElseThrow(()-> new NotFoundException("Team Two Not Found"));
        Team teamSupported = teamRepository.findById(matchRequestDTO.getTeam_supported()).orElseThrow(()-> new NotFoundException("Team Supported Not Found"));

        Optional<Match> matchExisting = matchRepository.findByMatchForTeamOneTeamTwoDate(teamOne, teamTwo, matchRequestDTO.getDate());

        if(matchExisting.isPresent()){
            throw new BusinessRuleException("Teams have already played on this date.");
        }

        if(teamOne.getId() == teamSupported.getId() || teamTwo.getId() == teamSupported.getId()){
            Match match = new Match();
            match.setDate(matchRequestDTO.getDate());
            match.setTeamOne(teamOne);
            match.setTeamTwo(teamTwo);
            match.setScoreTeamOne(matchRequestDTO.getScoreTeamOne());
            match.setScoreTeamTwo(matchRequestDTO.getScoreTeamTwo());
            match.setTeam_supported(teamSupported);
            return match;
        }else{
            throw new BusinessRuleException("Invalid supported team");
        }
    }

    public MatchResponseDTO getMatchById(Long id) {
        return matchRepository.findById(id).map(match -> new MatchResponseDTO(match)).orElseThrow(() -> new NotFoundException("Match not found"));
    }

    public Page<MatchResponseDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Match> pageMatch = matchRepository.findAll(pageable);
        return convertToDtoPage(pageMatch);
    }

    public Page<MatchResponseDTO> convertToDtoPage(Page<Match> pageMatch){
        List<MatchResponseDTO> listMetch = pageMatch.stream().map(match -> new MatchResponseDTO(match)).collect(Collectors.toList());
        return new PageImpl<>(listMetch, PageRequest.of(pageMatch.getNumber(), pageMatch.getSize()), pageMatch.getTotalElements());
    }
}
