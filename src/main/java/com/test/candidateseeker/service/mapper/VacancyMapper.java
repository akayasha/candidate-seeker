package com.test.candidateseeker.service.mapper;

import com.test.candidateseeker.model.Vacancy;
import com.test.candidateseeker.service.dto.VacancyDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface VacancyMapper extends EntityMapper<VacancyDTO,Vacancy> {

    Vacancy toEntity(VacancyDTO dto);

    VacancyDTO toDto(Vacancy vacancy);
}
