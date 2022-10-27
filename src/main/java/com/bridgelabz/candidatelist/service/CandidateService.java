package com.bridgelabz.candidatelist.service;

import com.bridgelabz.candidatelist.dto.CandidateDTO;
import com.bridgelabz.candidatelist.exception.CandidateException;
import com.bridgelabz.candidatelist.model.BankDetailsData;
import com.bridgelabz.candidatelist.model.CandidateData;
import com.bridgelabz.candidatelist.repository.CandidateRepository;
import com.bridgelabz.candidatelist.util.EmailSenderService;
import com.bridgelabz.candidatelist.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CandidateService implements ICandidateService {


    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private EmailSenderService mailService;
    @Autowired
    private TokenUtil util;


    @Override
    public String createCandidate(CandidateDTO candidateDTO) {
        CandidateData candidateData = new CandidateData(candidateDTO);
        candidateRepository.save(candidateData);
        String token = util.createToken(candidateData.getCandidateId());
        mailService.sendEmail("vigneshjmax@gmail.com", "Candidate Added", "Registered Successfully, "
                + candidateData.getFirstName() + token);

        return token;
    }

    @Override
    public CandidateData viewProfile(String token) {
        int id = util.decodeToken(token);
        Optional<CandidateData> getUser = candidateRepository.findById(id);
        if (getUser.isPresent()) {
            mailService.sendEmail("Vigneshjmax@gmail.com", "Test Email", "Get Your Data With This Token "
                    + getUser.get().getEmail() + token);

            return getUser.get();
        } else {
            throw new CandidateException("Record For Provided UserId Is Not Found");
        }
    }

    @Override
    public Long countOfHired(String status) {
        List<CandidateData> candidateData = candidateRepository.findCandidateByStatus(status);
        if (!candidateData.isEmpty()) {
            long listOfCandidate = candidateData.size();
            System.out.println(listOfCandidate);
            mailService.sendEmail("Vigneshjmax@gmail.com", "Test Email", "Get Your Data With This Token "
                    + candidateData +" "+ status);

            return listOfCandidate;
        } else {
            System.out.println("Exception ...Token Not Found!");
            return null;
        }
    }

    public List<CandidateData> listOfHired(String status) {
        List<CandidateData> isCandidatePresent = candidateRepository.findCandidateByStatus(status);
        if (!isCandidatePresent.isEmpty()) {
            List<CandidateData> listOfCandidate = candidateRepository.findAll();
            mailService.sendEmail("Vigneshjmax@gmail.com", "Test Email", "Get Your Data With This Token "
                    + isCandidatePresent+ " " + status);

            return listOfCandidate;
        } else {
            System.out.println("Exception ...Token Not Found!");
            return null;
        }
    }

    @Override
    public void deleteCandidate(int id) {
        Optional<CandidateData> candidateData = candidateRepository.findById(id);
        if (candidateData.isPresent()) {
            candidateRepository.deleteById(id);
        } else {
            throw new CandidateException("Bank Record Does Not Found");

        }
    }
}