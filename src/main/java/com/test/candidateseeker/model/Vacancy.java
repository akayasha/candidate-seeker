package com.test.candidateseeker.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "vacancy")
public class Vacancy {

    @Id
    private String id;

    @Field("vacancy_id")
    private Long vacancyId;

    @Field("vacancy_name")
    private String vacancyName;

    @Field("min_age")
    private Integer minAge;

    @Field("max_age")
    private Integer maxAge;

    @Field("requirement_gender")
    private String requirementGender;

    @Field("created_date")
    private Date createdDate;

    @Field("expired_date")
    private Date expiredDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
