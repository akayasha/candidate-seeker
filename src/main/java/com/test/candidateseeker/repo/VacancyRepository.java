package com.test.candidateseeker.repo;

import com.test.candidateseeker.model.Vacancy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface VacancyRepository extends MongoRepository<Vacancy,Long> {

    void deleteByVacancyId(Long vacancyId);

    Optional<Vacancy> findByVacancyId(Long vacancyId);

}
