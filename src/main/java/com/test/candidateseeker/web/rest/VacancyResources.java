package com.test.candidateseeker.web.rest;

import com.test.candidateseeker.model.Vacancy;
import com.test.candidateseeker.service.VacancyService;
import com.test.candidateseeker.service.dto.GeneralBody;
import com.test.candidateseeker.service.dto.VacancyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/vacancies")
public class VacancyResources {

    @Autowired
    private VacancyService vacancyService;


    @PostMapping("/save")
    public ResponseEntity<GeneralBody> addVacancy(@RequestBody VacancyDTO vacancyDTO) {
        VacancyDTO savedVacancy = vacancyService.addVacancy(vacancyDTO);
        return ResponseEntity.ok()
                .body(new GeneralBody(
                        200,
                        "Success",
                        "Vacancy saved successfully",
                        savedVacancy
                ));
    }

    @DeleteMapping("/delete/{vacancyId}")
    public ResponseEntity<GeneralBody> deleteVacancy(@PathVariable("vacancyId") Long vacancyId) {
        vacancyService.deleteVacancyById(vacancyId);
        return ResponseEntity.ok()
                .body(new GeneralBody(
                        200,
                        "Success",
                        "Vacancy deleted successfully",
                        null
                ));
    }

    @PutMapping("/edit/{vacancyId}")
    public ResponseEntity<GeneralBody> editVacancy(@PathVariable("vacancyId") Long vacancyId, @RequestBody VacancyDTO vacancyDTO) {
        VacancyDTO updatedVacancy = vacancyService.editVacancyById(vacancyId, vacancyDTO);

        return ResponseEntity.ok()
                .body(new GeneralBody(
                        200,
                        "Success",
                        "Vacancy edit successfull",
                        updatedVacancy
                ));
    }

    @GetMapping
    public ResponseEntity<GeneralBody> getAllVacancies(@RequestParam(value = "keyword", required = false) String keyword,
                                                         @RequestParam(value = "createdDate", required = false) Date createdDate,
                                                         Pageable pageable) {

        Page<Vacancy> vacancies = vacancyService.getAllVacancy(keyword, createdDate, pageable);
        return ResponseEntity.ok()
                .body(new GeneralBody(
                        200,
                        "Success",
                        "Ok",
                        vacancies
                ));
    }
}