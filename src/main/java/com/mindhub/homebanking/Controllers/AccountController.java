package com.mindhub.homebanking.Controllers;


import com.mindhub.homebanking.DTO.AccountDTO;
import com.mindhub.homebanking.DTO.ClientDTO;
import com.mindhub.homebanking.Models.Account;
import com.mindhub.homebanking.Models.Card;
import com.mindhub.homebanking.Models.Client;
import com.mindhub.homebanking.Repositories.AccountRepository;
import com.mindhub.homebanking.Repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RepositoryRestResource
@RequestMapping("/api")
public class AccountController {

        @Autowired
        private AccountRepository accountRepository;
        @Autowired
        private ClientRepository clientRepository;

        @RequestMapping("/accounts")
        public List<AccountDTO> getAccounts() {
            return accountRepository.findAll().stream().map(AccountDTO::new).collect(toList());
        }
        @RequestMapping("/accounts/{id}")
        public AccountDTO getAccount(@PathVariable Long id) {
            return accountRepository.findById(id).map(AccountDTO::new).orElse(null);
        }

        public String getAccountNumber(int min, int max) {
                int randomN = (int)((Math.random()*max)+min);
                return ("mhh-" + randomN);
        }

        @RequestMapping(path = "/clients/current/accounts", method = RequestMethod.POST)

        public ResponseEntity<Object> newAccount(Authentication authentication) {

                Client client = clientRepository.findByEmail(authentication.getName());
                if (client.getAccounts().size() <= 2) {
                        Account account = new Account(getAccountNumber(1, 999998), LocalDateTime.now(), 5000);
                        client.addAccounts(account);
                        accountRepository.save(account);
                        return new ResponseEntity<>(HttpStatus.CREATED);
                }
                return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        @RequestMapping(path = "/clients/current/accounts", method = RequestMethod.PUT)

        public void deleteAccounts(@RequestParam String accountNumber) {
                Account account = accountRepository.findByNumber(accountNumber);
                accountRepository.delete(account);
        }
}
