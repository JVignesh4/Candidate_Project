package com.bridgelabz.candidatelist.service;

import com.bridgelabz.candidatelist.dto.BankAccountDTO;
import com.bridgelabz.candidatelist.exception.CandidateException;
import com.bridgelabz.candidatelist.model.BankDetailsData;
import com.bridgelabz.candidatelist.model.CandidateData;
import com.bridgelabz.candidatelist.repository.BankDetailsRepository;
import com.bridgelabz.candidatelist.repository.CandidateRepository;
import com.bridgelabz.candidatelist.util.EmailSenderService;
import com.bridgelabz.candidatelist.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class BankDetailsService implements IBankDetailsService {


    @Autowired
    private BankDetailsRepository bankDetailsRepository;
    @Autowired
    private EmailSenderService mailService;
    @Autowired
    private TokenUtil util;

    @Override
    public String addBankDetails(BankAccountDTO bankAccountDTO) {
        BankDetailsData bankDetailsData = new BankDetailsData(bankAccountDTO);
        bankDetailsRepository.save(bankDetailsData);
        String token = util.createToken(bankDetailsData.getUserId());
        mailService.sendEmail("vigneshjmax@gmail.com", "Bank details Added", "Registered Successfully, "
                + bankDetailsData.getBankName() +" " + token);
        return token;
    }

    @Override
    public BankDetailsData viewBankDetails(String token) {
        int id = util.decodeToken(token);
        Optional<BankDetailsData> getUser = bankDetailsRepository.findById(id);
        if (getUser.isPresent()) {
            mailService.sendEmail("Vigneshjmax@gmail.com", "Bank details Email", "Get Your Bank Data With This Token "
                    + getUser.get() + " "+ token);

            return getUser.get();
        } else {
            throw new CandidateException("Record For Provided UserId Is Not Found");
        }
    }

    @Override
    public void deleteBankDetails(int id) {
        Optional<BankDetailsData> bankData = bankDetailsRepository.findById(id);
        if (bankData.isPresent()) {
            bankDetailsRepository.deleteById(id);
        } else {
            throw new CandidateException("Bank Record Does Not Found");
        }
    }


    @Override
    public BankDetailsData updateBankDetails(int userId, BankAccountDTO accountDTO) {
        Optional<BankDetailsData> bankDetailsData = bankDetailsRepository.findById(userId);
        if(bankDetailsData.isEmpty()) {
            throw new CandidateException("Bank details doesn't exist");
        }else{
            BankDetailsData newData = new BankDetailsData(userId,accountDTO);
            bankDetailsRepository.save(newData);
            log.info("Bank Details updated successfully");
            return  newData;
        }
    }


}
