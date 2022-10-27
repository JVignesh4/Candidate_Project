package com.bridgelabz.candidatelist.service;

import com.bridgelabz.candidatelist.dto.BankAccountDTO;
import com.bridgelabz.candidatelist.model.BankDetailsData;

public interface IBankDetailsService {

    BankDetailsData updateBankDetails(int userId, BankAccountDTO accountDTO);

    String addBankDetails(BankAccountDTO bankAccountDTO);

    BankDetailsData viewBankDetails(String token);
    void deleteBankDetails(int id);
}
