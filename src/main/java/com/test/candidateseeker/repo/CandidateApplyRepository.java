package com.test.candidateseeker.repo;

import com.test.candidateseeker.model.CandidateApply;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface CandidateApplyRepository extends MongoRepository<CandidateApply,Long> {

    Optional<CandidateApply> findByApplyId(Long applyId);
}
