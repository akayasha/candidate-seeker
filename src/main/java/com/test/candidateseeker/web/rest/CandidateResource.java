package com.test.candidateseeker.web.rest;


import com.test.candidateseeker.model.Candidate;
import com.test.candidateseeker.service.CandidateService;
import com.test.candidateseeker.service.dto.CandidateDTO;
import com.test.candidateseeker.service.dto.GeneralBody;
import org.springframework.data.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/candidate")
public class CandidateResource {

    private final Logger log = LoggerFactory.getLogger(CandidateResource.class);

    @Autowired
    CandidateService candidateService;

    @GetMapping("/get-all")
    public ResponseEntity<GeneralBody> getAllCandidates(@RequestParam(value = "keyword", required = false) String keyword,
                                                        @RequestParam(value = "dob", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date dob,
                                                        Pageable pageable) {
        log.debug("REST request to get all Candidates");

        Page<Candidate> candidatesPage = candidateService.getAllCandidates(keyword, dob, pageable);

        return ResponseEntity.ok()
                .body(new GeneralBody(
                        200,
                        "Success",
                        "Ok",
                        candidatesPage
                ));
    }


    @PostMapping("/save")
    public ResponseEntity<GeneralBody> saveCandidate(@RequestBody CandidateDTO candidateDTO) {
        CandidateDTO savedCandidate = candidateService.addCandidate(candidateDTO);
        return ResponseEntity.ok()
                .body(new GeneralBody(
                        200,
                        "Success",
                        "Candidate saved successfully",
                        savedCandidate
                ));
    }

    @PutMapping("/edit")
    public ResponseEntity<GeneralBody> editCandidate(@RequestBody CandidateDTO candidateDTO) {
        CandidateDTO updatedCandidate = candidateService.editCandidate(candidateDTO);
        return ResponseEntity.ok()
                .body(new GeneralBody(
                        200,
                        "Success",
                        "Candidate updated successfully",
                        updatedCandidate
                ));
    }

    @DeleteMapping("/delete/{candidateId}")
    public ResponseEntity<GeneralBody> deleteCandidate(@PathVariable Long candidateId) {
        candidateService.deleteCandidate(candidateId);
        return ResponseEntity.ok()
                .body(new GeneralBody(
                        200,
                        "Success",
                        "Candidate deleted successfully",
                        null
                ));
    }

    @GetMapping("/detail/{candidateId}")
    public ResponseEntity<GeneralBody> detailCandidate(@PathVariable Long candidateId) {
        CandidateDTO data = candidateService.getDetailById(candidateId);

        return ResponseEntity.ok()
                .body(new GeneralBody(
                        200,
                        "Success",
                        "Ok",
                        data
                ));
    }

}
