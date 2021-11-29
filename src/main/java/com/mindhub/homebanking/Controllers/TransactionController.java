package com.mindhub.homebanking.Controllers;

import com.mindhub.homebanking.Models.Account;
import com.mindhub.homebanking.Models.Client;
import com.mindhub.homebanking.Models.Transaction;
import com.mindhub.homebanking.Models.TransactionType;
import com.mindhub.homebanking.Repositories.AccountRepository;
import com.mindhub.homebanking.Repositories.ClientRepository;
import com.mindhub.homebanking.Repositories.TransactionRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@RestController
//@RepositoryRestResource
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    //@RequestMapping(path = "/transactions", method = RequestMethod.POST)
    @PostMapping("/transactions")

    public ResponseEntity<Object> transfer(
            @RequestParam String originAccountNumber, @RequestParam String destinationAccountNumber,
            @RequestParam Double amount, @RequestParam String description,
            Authentication authentication) {
            if (amount  <= 0) {
                return new ResponseEntity<>("Amount must be higher than 0", HttpStatus.FORBIDDEN);
            }
            if (description.isEmpty() || originAccountNumber.isEmpty() || destinationAccountNumber.isEmpty()) {
                return new ResponseEntity<>("All fields are required", HttpStatus.FORBIDDEN);
            }
            if (originAccountNumber.equals(destinationAccountNumber)) {
                return new ResponseEntity<>("Can not sent to the same account", HttpStatus.FORBIDDEN);
            }
            if (accountRepository.findByNumber(originAccountNumber) == null) {
                return new ResponseEntity<>("Origin Account does not exist", HttpStatus.FORBIDDEN);
            }
            if (accountRepository.findByNumber(originAccountNumber).getUser() != clientRepository.findByEmail(authentication.getName())) {
                return new ResponseEntity<>("Account does not belong to current user", HttpStatus.FORBIDDEN);
            }
            if (accountRepository.findByNumber(destinationAccountNumber) == null) {
                return new ResponseEntity<>("Destination Account does not exist", HttpStatus.FORBIDDEN);
            }
            if (amount > accountRepository.findByNumber(originAccountNumber).getBalance()) {
                return new ResponseEntity<>("Insufficient Balance", HttpStatus.FORBIDDEN);
            }

            Client client = clientRepository.findByEmail(authentication.getName());

            Account accountFrom = accountRepository.findByNumber(originAccountNumber);
            Account accountTo = accountRepository.findByNumber(destinationAccountNumber);

            Transaction transactionFrom = new Transaction(amount * -1, description + " " + "-" + " " + accountTo.getNumber(), TransactionType.DEBIT, accountFrom);
            transactionRepository.save(transactionFrom);
            Transaction transactionTo = new Transaction(amount * 1, description + " " + "-" + " " + accountFrom.getNumber(), TransactionType.CREDIT, accountTo);
            transactionRepository.save(transactionTo);

            //resta de la primera
            accountFrom.setBalance(accountFrom.getBalance() - amount);
            accountRepository.save(accountFrom);
            //suma a la segunda
            accountTo.setBalance(accountTo.getBalance() + amount);
            accountRepository.save(accountTo);

            return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
