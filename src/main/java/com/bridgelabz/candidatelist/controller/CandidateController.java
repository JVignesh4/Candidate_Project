package com.bridgelabz.candidatelist.controller;

import com.bridgelabz.candidatelist.dto.CandidateDTO;
import com.bridgelabz.candidatelist.dto.ResponseDTO;
import com.bridgelabz.candidatelist.model.CandidateData;
import com.bridgelabz.candidatelist.repository.CandidateRepository;
import com.bridgelabz.candidatelist.service.ICandidateService;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/candidate")
public class CandidateController {


    @Autowired
    private ICandidateService candidateService;

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createCandidate(@Valid @RequestBody CandidateDTO candidateDTO) {
        String candidateData =candidateService.createCandidate(candidateDTO);
        ResponseDTO responseDTO = new ResponseDTO("Created Candidate Data Successfully", candidateData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
     }

    @GetMapping("/getby/{token}")
    public ResponseEntity<ResponseDTO> viewProfile(@PathVariable String token){
        CandidateData candidateData = candidateService.viewProfile(token);
        ResponseDTO responseDTO = new ResponseDTO("Get Call Success For Id Successfully", candidateData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/count/{status}")
    public ResponseEntity<ResponseDTO> countOfHired(@PathVariable String status){
        long candidateData = candidateService.countOfHired(status);
        ResponseDTO responseDTO = new ResponseDTO("Get Call Success For Id Successfully", candidateData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/list/{status}")
    public ResponseEntity<ResponseDTO> listOfHired(@PathVariable String status){
        List<CandidateData> candidateData = candidateService.listOfHired(status);
        ResponseDTO responseDTO = new ResponseDTO("Get Call Success For Id Successfully", candidateData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteCandidate(@PathVariable int id){
        candidateService.deleteCandidate(id);
        ResponseDTO responseDTO = new ResponseDTO("Deleted Successfully", id);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/load")
    public BatchStatus load() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(job, parameters);

        System.out.println("JobExecution: " + jobExecution.getStatus());

        System.out.println("Batch is Running...");
        while (jobExecution.isRunning()) {
            System.out.println("...");
        }

        return jobExecution.getStatus();
    }

}
