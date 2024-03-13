package com.test.candidateseeker.service.dto;

import java.util.Date;

public class VacancyDTO {

    private Long vacancyId;

    private String vacancyName;
    private Integer minAge;
    private Integer maxAge;
    private String requirementGender;
    private Date createdDate;
    private Date expiredDate;

    public Long getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(Long vacancyId) {
        this.vacancyId = vacancyId;
    }

    public String getVacancyName() {
        return vacancyName;
    }

    public void setVacancyName(String vacancyName) {
        this.vacancyName = vacancyName;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    public String getRequirementGender() {
        return requirementGender;
    }

    public void setRequirementGender(String requirementGender) {
        this.requirementGender = requirementGender;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }
}
