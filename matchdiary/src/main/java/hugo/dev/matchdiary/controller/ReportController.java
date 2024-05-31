package hugo.dev.matchdiary.controller;

import hugo.dev.matchdiary.dto.report.ReportResponseDTO;
import hugo.dev.matchdiary.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/report")
public class ReportController {


    @Autowired
    ReportService reportsService;

    @GetMapping(value = "{id}")
    public ResponseEntity<ReportResponseDTO> getAllReports(@PathVariable Long id){
        return new ResponseEntity<>(reportsService.getTeamReports(id), HttpStatus.OK);
    }

}
