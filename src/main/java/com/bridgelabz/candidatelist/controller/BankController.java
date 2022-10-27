package com.bridgelabz.candidatelist.controller;

import com.bridgelabz.candidatelist.dto.BankAccountDTO;
import com.bridgelabz.candidatelist.dto.ResponseDTO;
import com.bridgelabz.candidatelist.model.BankDetailsData;
import com.bridgelabz.candidatelist.service.BankDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/bankDetails")
public class BankController {

    @Autowired
    private BankDetailsService bankDetailsService;

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> addBankDetails(@Valid @RequestBody BankAccountDTO bankAccountDTO) {
        String bankDetailsData =bankDetailsService.addBankDetails(bankAccountDTO);
        ResponseDTO responseDTO = new ResponseDTO("Created Bank Data Successfully", bankDetailsData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/getby/{token}")
    public ResponseEntity<ResponseDTO> viewBankDetails(@PathVariable String token){
        BankDetailsData bankData = bankDetailsService.viewBankDetails(token);
        ResponseDTO responseDTO = new ResponseDTO("Get Call Success For Id Successfully", bankData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<ResponseDTO> updateBankData(@PathVariable int userId,@Valid @RequestBody BankAccountDTO accountDTO){
        BankDetailsData bankData = bankDetailsService.updateBankDetails(userId,accountDTO);
        ResponseDTO responseDTO = new ResponseDTO("Get Call Success For Id Successfully", bankData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteCandidate(@PathVariable int id){
        bankDetailsService.deleteBankDetails(id);
        ResponseDTO responseDTO = new ResponseDTO("Deleted Successfully", id);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
}
