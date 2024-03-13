package com.test.candidateseeker.service;

import com.test.candidateseeker.model.Candidate;
import com.test.candidateseeker.repo.CandidateRepository;
import com.test.candidateseeker.service.dto.CandidateDTO;
import com.test.candidateseeker.service.mapper.CandidateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.bson.Document;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.stereotype.Service;




import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CandidateService {

    private final Logger log = LoggerFactory.getLogger(CandidateService.class);

    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    CandidateMapper candidateMapper;

    public CandidateDTO addCandidate(CandidateDTO candidateDTO) {
        Candidate candidate = candidateMapper.toEntity(candidateDTO);
        candidate = candidateRepository.save(candidate);
        return candidateMapper.toDto(candidate);
    }

    public CandidateDTO editCandidate(CandidateDTO candidateDTO) {
        if (candidateDTO.getCandidateId() == null) {
            throw new IllegalArgumentException("CandidateId cannot be null for editing.");
        }

        Candidate existingCandidate = candidateRepository.findByCandidateId(candidateDTO.getCandidateId())
                .orElseThrow(() -> new IllegalArgumentException("Candidate not found with id: " + candidateDTO.getCandidateId()));

        Candidate updatedCandidate = candidateMapper.toEntity(candidateDTO);
        // Preserve the ID from existing candidate
        updatedCandidate.setId(existingCandidate.getId());
        updatedCandidate = candidateRepository.save(updatedCandidate);

        return candidateMapper.toDto(updatedCandidate);
    }

    public void deleteCandidate(Long candidateId) {
        Candidate existingCandidate = candidateRepository.findByCandidateId(candidateId)
                .orElseThrow(() -> new IllegalArgumentException("Candidate not found with id: " + candidateId));
        candidateRepository.delete(existingCandidate);
    }

    public Page<Candidate> getAllCandidates(String keyword, Date dob, Pageable pageable) {
        List<Candidate> candidates = candidateRepository.findAll();

        if (keyword != null && !keyword.isEmpty()) {
            candidates = candidates.stream()
                    .filter(candidate -> candidate.getFullName().toLowerCase().contains(keyword.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (dob != null) {
            candidates = candidates.stream()
                    .filter(candidate -> candidate.getDob().equals(dob))
                    .collect(Collectors.toList());
        }

        int start = (int) pageable.getOffset();
        int end = (start + pageable.getPageSize()) > candidates.size() ? candidates.size() : (start + pageable.getPageSize());

        return new PageImpl<>(candidates.subList(start, end), pageable, candidates.size());
    }

    public CandidateDTO getDetailById(Long candidateId) {
        Optional<Candidate> candidateOptional = candidateRepository.findByCandidateId(candidateId);

        if (candidateOptional.isPresent()) {
            Candidate candidate = candidateOptional.get();
            CandidateDTO candidateDTO = new CandidateDTO();
            candidateDTO.setCandidateId(candidate.getCandidateId());
            candidateDTO.setDob(candidate.getDob());
            candidateDTO.setGender(candidate.getGender());
            candidateDTO.setId(candidate.getId());
            return candidateDTO;
        } else {
            throw new IllegalArgumentException("Candidate not found with id: " + candidateId);
        }
    }
}
