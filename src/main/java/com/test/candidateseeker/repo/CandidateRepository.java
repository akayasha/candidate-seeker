package com.test.candidateseeker.repo;

import com.test.candidateseeker.model.Candidate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface CandidateRepository extends MongoRepository<Candidate,Long>{

    Optional<Candidate> findByCandidateId(Long candidateId);
}
