package com.bridgelabz.candidatelist.model;

import com.bridgelabz.candidatelist.dto.CandidateDTO;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "employee_list")
public class CandidateData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int candidateId;
    private String firstName;
    private String lastName;
    private String address;
    private String status;

    private String email;

    public CandidateData(int id, CandidateDTO candidateDto) {
        this.candidateId = id;
        this.firstName = candidateDto.getFirstName();
        this.lastName = candidateDto.getLastName();
        this.address = candidateDto.getAddress();
        this.status = candidateDto.getStatus();
    }

    public CandidateData(CandidateDTO candidateDto) {
        this.firstName = candidateDto.getFirstName();
        this.lastName = candidateDto.getLastName();
        this.address = candidateDto.getAddress();
        this.status = candidateDto.getStatus();
    }


    public CandidateData() {

    }
}
