package com.test.candidateseeker.service.mapper;

import com.test.candidateseeker.model.CandidateApply;
import com.test.candidateseeker.service.dto.CandidateApplyDTO;
import com.test.candidateseeker.service.dto.CandidateDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface CandidateApplyMapper extends EntityMapper<CandidateApplyDTO, CandidateApply> {

    CandidateApply toEntity(CandidateApplyDTO dto);

    CandidateApplyDTO toDto(CandidateApply candidateApply);
}
