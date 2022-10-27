package com.bridgelabz.candidatelist.repository;

import com.bridgelabz.candidatelist.model.CandidateData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface CandidateRepository extends JpaRepository<CandidateData,Integer> {

    @Query(value = "select * from employee_list where status = ?1",nativeQuery = true)
    List<CandidateData> findByStatus(String status);

    List<CandidateData> findCandidateByStatus(String status);
}
