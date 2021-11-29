package com.mindhub.homebanking.Controllers;

import com.mindhub.homebanking.DTO.LoanApplicationDTO;
import com.mindhub.homebanking.DTO.LoanDTO;
import com.mindhub.homebanking.Models.*;
import com.mindhub.homebanking.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class LoanController {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private ClientLoanRepository clientLoanRepository;


    public Double amountRequestedPlus (double amountReq) {
        amountReq = ((20 * amountReq)/100) + amountReq;
        return amountReq;
    }

    @GetMapping("/loans")

    public List<LoanDTO> getLoans () {
        return loanRepository.findAll().stream().map(LoanDTO::new).collect(toList());
    }

    @Transactional
    @PostMapping("/loans")



    public ResponseEntity<Object> newLoan(
            Authentication authentication, @RequestBody LoanApplicationDTO loanApplicationDTO
            ) {

        if (loanApplicationDTO.getAmountRequest()  <= 0) {
            return new ResponseEntity<>("Amount must be higher than 0", HttpStatus.FORBIDDEN);
        }
        if (loanApplicationDTO.getLoanId() == null || loanApplicationDTO.getPayments() == null || loanApplicationDTO.getDestinationAccountNumber().isEmpty()) {
            return new ResponseEntity<>("All fields are required", HttpStatus.FORBIDDEN);
        }
        if (loanRepository.findById(loanApplicationDTO.getLoanId()).isEmpty()) {
            return new ResponseEntity<>("Loan type is not available", HttpStatus.FORBIDDEN);
        }
        if (loanApplicationDTO.getAmountRequest() > loanRepository.findById(loanApplicationDTO.getLoanId()).get().getMaxAmount()) {
            return new ResponseEntity<>("Amount requested is more than the max amount available", HttpStatus.FORBIDDEN);
        }
        if (accountRepository.findByNumber(loanApplicationDTO.getDestinationAccountNumber()) == null) {
            return new ResponseEntity<>("Destination Account does not exist", HttpStatus.FORBIDDEN);
        }
        if (!accountRepository.findByNumber(loanApplicationDTO.getDestinationAccountNumber()).getUser().equals(clientRepository.findByEmail(authentication.getName()))) {
            return new ResponseEntity<>("Account does not belong to current user", HttpStatus.FORBIDDEN);
        }
        if (!loanRepository.findById(loanApplicationDTO.getLoanId()).get().getPayments().contains(loanApplicationDTO.getPayments())) {
            return new ResponseEntity<>("Option do not available for that loan type", HttpStatus.FORBIDDEN);
        }

        Client client = clientRepository.findByEmail(authentication.getName());

        Account accountTo = accountRepository.findByNumber(loanApplicationDTO.getDestinationAccountNumber());

        Loan loan = loanRepository.getById(loanApplicationDTO.getLoanId());

        double amountRequested = amountRequestedPlus(loanApplicationDTO.getAmountRequest());

        clientLoanRepository.save(new ClientLoan(client, loan, amountRequested, loanApplicationDTO.getPayments()));

        transactionRepository.save(new Transaction(loanApplicationDTO.getAmountRequest(), "Loan approved", TransactionType.CREDIT, accountTo));

        accountTo.setBalance(accountTo.getBalance() + loanApplicationDTO.getAmountRequest());
        accountRepository.save(accountTo);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
