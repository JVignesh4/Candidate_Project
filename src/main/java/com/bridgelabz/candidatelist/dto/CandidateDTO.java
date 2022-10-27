package com.bridgelabz.candidatelist.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Pattern;

@Data
public class CandidateDTO {

    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "FirstName is Invalid")
    private final String firstName;

    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "LastName is Invalid")
    private String lastName;

    private String address;
    private String status;

    private String email;

    public CandidateDTO(String firstName, String lastName, String address, String status,String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.status = status;
        this.email=email;
    }
}
