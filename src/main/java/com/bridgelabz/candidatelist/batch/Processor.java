package com.bridgelabz.candidatelist.batch;


import com.bridgelabz.candidatelist.model.CandidateData;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class Processor implements ItemProcessor<CandidateData, CandidateData> {

    public Processor() {

    }

    @Override
    public CandidateData process(CandidateData CandidateData) throws Exception {
        return CandidateData;
    }
}

