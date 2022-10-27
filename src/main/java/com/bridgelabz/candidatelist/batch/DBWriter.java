package com.bridgelabz.candidatelist.batch;

import com.bridgelabz.candidatelist.model.CandidateData;
import com.bridgelabz.candidatelist.repository.CandidateRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBWriter implements ItemWriter<CandidateData> {

    private CandidateRepository candidateRepository;

    @Autowired
    public DBWriter (CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public void write(List<? extends CandidateData> users) throws Exception{
        System.out.println("Data Saved for Users: " + users);
        candidateRepository.saveAll(users);
    }
}
