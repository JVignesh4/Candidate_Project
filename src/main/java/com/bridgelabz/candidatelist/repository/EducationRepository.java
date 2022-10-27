package com.bridgelabz.candidatelist.repository;

import com.bridgelabz.candidatelist.model.EducationDetailsData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends JpaRepository<EducationDetailsData, Integer> {

}
