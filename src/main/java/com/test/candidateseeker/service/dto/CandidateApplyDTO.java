package com.test.candidateseeker.service.dto;

import java.util.Date;

public class CandidateApplyDTO {

    private String applyId;
    private CandidateDTO candidate;
    private VacancyDTO vacancy;
    private Date applyDate;
    private Date createdDate;

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public CandidateDTO getCandidate() {
        return candidate;
    }

    public void setCandidate(CandidateDTO candidate) {
        this.candidate = candidate;
    }

    public VacancyDTO getVacancy() {
        return vacancy;
    }

    public void setVacancy(VacancyDTO vacancy) {
        this.vacancy = vacancy;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
