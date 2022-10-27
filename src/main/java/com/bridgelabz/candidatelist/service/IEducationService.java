package com.bridgelabz.candidatelist.service;

import com.bridgelabz.candidatelist.dto.EducationDTO;
import com.bridgelabz.candidatelist.model.EducationDetailsData;

public interface IEducationService {

    String addEducationDetails(EducationDTO educationDTO);

    EducationDetailsData viewEducationDetails(String token);

    EducationDetailsData updateEducationDetails(int id, EducationDTO educationDTO);

}
