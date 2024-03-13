package com.test.candidateseeker.web.rest;

import com.test.candidateseeker.model.CandidateApply;
import com.test.candidateseeker.service.CandidateApplyService;
import com.test.candidateseeker.service.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/candidate-apply")
public class CandidateApplyResource {

    @Autowired
    private CandidateApplyService candidateApplyService;

    @PostMapping("/apply")
    public ResponseEntity<GeneralBody> applyCandidateToVacancy(@RequestBody ApplyRequest request) {
        Long candidateId = request.getCandidateId();
        Long vacancyId = request.getVacancyId();

        try {
            CandidateApplyDTO appliedCandidate = candidateApplyService.applyCandidateToVacancy(candidateId, vacancyId);
            return ResponseEntity.ok().body(new GeneralBody(200, "Success", "Candidate successfully applied to vacancy.", appliedCandidate));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new GeneralBody(400, "Error", e.getMessage(), null));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<GeneralBody> getAllCandidateApplications(@RequestParam(value = "sortBy", defaultValue = "applyDate") String sortBy) {
            List<CandidateApplyDTO> candidateApplyDTOs = candidateApplyService.getAllCandidateApplications(sortBy);
            return ResponseEntity.ok()
                    .body(new GeneralBody(
                            200,
                            "Success",
                            "Ok",
                            candidateApplyDTOs
                    ));
    }

    @DeleteMapping("/applications/{id}")
    public ResponseEntity<GeneralBody> deleteCandidateApplication(@PathVariable Long id) {
        try {
            candidateApplyService.deleteCandidateApplication(id);
            return ResponseEntity.ok().body(new GeneralBody(200, "Success", "Candidate application deleted successfully.", null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new GeneralBody(400, "Error", e.getMessage(), null));
        }
    }

    @PutMapping("/applications/{id}")
    public ResponseEntity<GeneralBody> editCandidateApplication(@PathVariable Long id, @RequestBody ApplyRequest request) {
        Long candidateId = request.getCandidateId();
        Long vacancyId = request.getVacancyId();

        try {
            CandidateApplyDTO editedApplication = candidateApplyService.editCandidateApplication(id, candidateId, vacancyId);
            return ResponseEntity.ok().body(new GeneralBody(200, "Success", "Candidate application edited successfully.", editedApplication));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new GeneralBody(400, "Error", e.getMessage(), null));
        }
    }

}
