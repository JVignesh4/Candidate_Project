package com.bridgelabz.candidatelist.service;

import com.bridgelabz.candidatelist.dto.CandidateDTO;
import com.bridgelabz.candidatelist.model.CandidateData;

import java.util.List;

public interface ICandidateService {

    String createCandidate(CandidateDTO candidateDTO);
    CandidateData viewProfile(String token);

    Long countOfHired(String status);

    List<CandidateData> listOfHired(String status);

    void deleteCandidate(int id);
}
