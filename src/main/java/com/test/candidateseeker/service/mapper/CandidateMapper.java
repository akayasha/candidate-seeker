package com.test.candidateseeker.service.mapper;

import com.test.candidateseeker.model.Candidate;
import com.test.candidateseeker.model.Vacancy;
import com.test.candidateseeker.service.dto.CandidateDTO;
import com.test.candidateseeker.service.dto.VacancyDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = {})
public interface CandidateMapper extends EntityMapper<CandidateDTO, Candidate>{

    Candidate toEntity(CandidateDTO dto);

    CandidateDTO toDto(Candidate candidate);

}
