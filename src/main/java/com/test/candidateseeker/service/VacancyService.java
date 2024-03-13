package com.test.candidateseeker.service;

import com.test.candidateseeker.model.Vacancy;
import com.test.candidateseeker.repo.VacancyRepository;
import com.test.candidateseeker.service.dto.VacancyDTO;
import com.test.candidateseeker.service.mapper.VacancyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VacancyService {

    @Autowired
    VacancyRepository vacancyRepository;

    @Autowired
    VacancyMapper vacancyMapper;

    public VacancyDTO addVacancy(VacancyDTO vacancyDTO) {
        Vacancy vacancy = vacancyMapper.toEntity(vacancyDTO);
        vacancy.setCreatedDate(new Date());
        vacancy = vacancyRepository.save(vacancy);
        return vacancyMapper.toDto(vacancy);
    }

    public void deleteVacancyById(Long vacancyId) {
        vacancyRepository.deleteByVacancyId(vacancyId);
    }

    public VacancyDTO editVacancyById(Long vacancyId, VacancyDTO vacancyDTO) {
        Optional<Vacancy> optionalVacancy = vacancyRepository.findByVacancyId(vacancyId);
        if (optionalVacancy.isPresent()) {
            Vacancy existingVacancy = optionalVacancy.get();
            existingVacancy.setVacancyName(vacancyDTO.getVacancyName());
            existingVacancy.setMinAge(vacancyDTO.getMinAge());
            existingVacancy.setMaxAge(vacancyDTO.getMaxAge());
            existingVacancy.setRequirementGender(vacancyDTO.getRequirementGender());
            existingVacancy.setExpiredDate(vacancyDTO.getExpiredDate());

            Vacancy updatedVacancy = vacancyRepository.save(existingVacancy);
            return vacancyMapper.toDto(updatedVacancy);
        } else {
            return null;
        }
    }

    public Page<Vacancy> getAllVacancy(String keyword, Date createdDate, Pageable pageable) {
        List<Vacancy> vacancies = vacancyRepository.findAll();

        if (keyword != null && !keyword.isEmpty()) {
            vacancies = vacancies.stream()
                    .filter(vacancy -> vacancy.getVacancyName().toLowerCase().contains(keyword.toLowerCase()))
                    .collect(Collectors.toList());
        }
        int start = (int) pageable.getOffset();
        int end = (start + pageable.getPageSize()) > vacancies.size() ? vacancies.size() : (start + pageable.getPageSize());

        return new PageImpl<>(vacancies.subList(start, end), pageable, vacancies.size());
    }
}
