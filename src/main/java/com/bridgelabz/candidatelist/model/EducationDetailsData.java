package com.bridgelabz.candidatelist.model;

import com.bridgelabz.candidatelist.dto.EducationDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="education_details")
public class EducationDetailsData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String degree;
    private String passedOut;
    private String percentage;

    //private String document;
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_Id")
    private CandidateData candidateData;

    public EducationDetailsData(int id,CandidateData candidateData, EducationDTO educationDTO) {
        this.id = id;
        this.candidateData=candidateData;
        this.degree = educationDTO.getDegree();
        this.passedOut = educationDTO.getPassedOut();
        this.percentage = educationDTO.getPercentage();
    }

    public EducationDetailsData(EducationDTO educationDTO) {
        this.degree = educationDTO.getDegree();
        this.passedOut = educationDTO.getPassedOut();
        this.percentage = educationDTO.getPercentage();
    }

    public EducationDetailsData() {

    }
}
