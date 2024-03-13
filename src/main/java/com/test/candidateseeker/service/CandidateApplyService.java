package com.test.candidateseeker.service;

import com.test.candidateseeker.model.Candidate;
import com.test.candidateseeker.model.CandidateApply;
import com.test.candidateseeker.model.Vacancy;
import com.test.candidateseeker.repo.CandidateApplyRepository;
import com.test.candidateseeker.repo.CandidateRepository;
import com.test.candidateseeker.repo.VacancyRepository;
import com.test.candidateseeker.service.dto.CandidateApplyDTO;
import com.test.candidateseeker.service.dto.CandidateDTO;
import com.test.candidateseeker.service.dto.VacancyDTO;
import com.test.candidateseeker.service.mapper.CandidateApplyMapper;
import com.test.candidateseeker.service.mapper.CandidateMapper;
import com.test.candidateseeker.service.mapper.VacancyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;

@Service
public class CandidateApplyService {

    @Autowired
    private CandidateApplyRepository candidateApplyRepository;
    @Autowired
    private CandidateMapper candidateMapper;
    @Autowired
    private VacancyMapper vacancyMapper;
    @Autowired
    private CandidateApplyMapper candidateApplyMapper;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private VacancyRepository vacancyRepository;

    public CandidateApplyDTO applyCandidateToVacancy(Long candidateId, Long vacancyId) {

        if (candidateId == null || vacancyId == null) {
            throw new IllegalArgumentException("Candidate ID or Vacancy ID is missing in the request.");
        }

        Candidate candidate = candidateRepository.findByCandidateId(candidateId)
                .orElseThrow(() -> new IllegalArgumentException("Candidate not found with id: " + candidateId));

        Vacancy vacancy = vacancyRepository.findByVacancyId(vacancyId)
                .orElseThrow(() -> new IllegalArgumentException("Vacancy not found with id: " + vacancyId));

        if (!checkRequirements(candidate, vacancy)) {
            throw new IllegalArgumentException("Candidate does not meet the requirements for this vacancy.");
        }

        CandidateApply candidateApply = new CandidateApply();
        candidateApply.setCandidate(candidate);
        candidateApply.setVacancy(vacancy);
        candidateApply.setApplyDate(new Date());
        candidateApply.setCreatedDate(new Date());
        return candidateApplyMapper.toDto(candidateApplyRepository.save(candidateApply));
    }

    public List<CandidateApplyDTO> getAllCandidateApplications(String sortBy) {
        Sort sort = Sort.by(Sort.Direction.ASC, sortBy);
        List<CandidateApply> candidateApplies = candidateApplyRepository.findAll(sort);
        return candidateApplyMapper.toDto(candidateApplies);
    }

    private boolean checkRequirements(Candidate candidate, Vacancy vacancy) {
        LocalDate dob = LocalDate.parse(candidate.getDob());
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(dob, currentDate).getYears();

        if (vacancy.getMinAge() != null && vacancy.getMinAge() > 0) {
            if (age < vacancy.getMinAge()) {
                return false;
            }
        }

        if (vacancy.getMaxAge() != null && vacancy.getMaxAge() > 0) {
            if (age > vacancy.getMaxAge()) {
                return false;
            }
        }

        if (!vacancy.getRequirementGender().equalsIgnoreCase("All") && !candidate.getGender().equalsIgnoreCase(vacancy.getRequirementGender())) {
            return false;
        }

        return true;
    }


    public void deleteCandidateApplication(Long applyId) {

        CandidateApply candidateApply = candidateApplyRepository.findByApplyId(applyId)
                .orElseThrow(() -> new IllegalArgumentException("Candidate application not found with id: " + applyId));

        candidateApplyRepository.delete(candidateApply);
    }

    public CandidateApplyDTO editCandidateApplication(Long candidateApplyId, Long candidateId, Long vacancyId) {
        CandidateApply candidateApply = candidateApplyRepository.findById(candidateApplyId)
                .orElseThrow(() -> new IllegalArgumentException("Candidate application not found with id: " + candidateApplyId));

        Candidate candidate = candidateRepository.findByCandidateId(candidateId)
                .orElseThrow(() -> new IllegalArgumentException("Candidate not found with id: " + candidateId));

        Vacancy vacancy = vacancyRepository.findByVacancyId(vacancyId)
                .orElseThrow(() -> new IllegalArgumentException("Vacancy not found with id: " + vacancyId));

        if (!checkRequirements(candidate, vacancy)) {
            throw new IllegalArgumentException("Candidate does not meet the requirements for this vacancy.");
        }

        candidateApply.setCandidate(candidate);
        candidateApply.setVacancy(vacancy);

        return candidateApplyMapper.toDto(candidateApplyRepository.save(candidateApply));
    }



}

