package com.mindhub.homebanking.Controllers;

import com.mindhub.homebanking.DTO.ClientDTO;
import com.mindhub.homebanking.Models.Account;
import com.mindhub.homebanking.Models.Client;
import com.mindhub.homebanking.Models.Loan;
import com.mindhub.homebanking.Repositories.AccountRepository;
import com.mindhub.homebanking.Repositories.ClientRepository;
//import jdk.internal.access.JavaLangInvokeAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RepositoryRestResource
@RequestMapping("/api")
public class ClientController {

        @Autowired
        private ClientRepository clientRepository;

        @Autowired
        private AccountRepository accountRepository;

        @Autowired

        private PasswordEncoder passwordEncoder;


        @RequestMapping("/clients")
        public List<ClientDTO> getClients() {
            return clientRepository.findAll().stream().map(ClientDTO::new).collect(toList());//findById
        }
        @RequestMapping("/clients/{id}")
        public ClientDTO getClient(@PathVariable Long id) {
                return clientRepository.findById(id).map(ClientDTO::new).orElse(null);
        }
        @RequestMapping("/clients/current")
        public ClientDTO getCurrent(Authentication authentication) {
                return new ClientDTO(clientRepository.findByEmail(authentication.getName()));
        }

        public String getAccountNumber(int min, int max) {
                int randomN = (int)((Math.random()*max)+min);
                return ("mhh-" + randomN);
        }


        @RequestMapping(path = "/clients", method = RequestMethod.POST)

        public ResponseEntity<Object> register(

                @RequestParam String firstName, @RequestParam String lastName,

                @RequestParam String email, @RequestParam String password) {


                if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                        return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
                }
                if (clientRepository.findByEmail(email) !=  null) {
                        return new ResponseEntity<>("Email is already in use", HttpStatus.FORBIDDEN);
                }
                Client client = new Client(firstName, lastName, email, passwordEncoder.encode(password));
                clientRepository.save(client);

                Account account = new Account(getAccountNumber(1, 999998), LocalDateTime.now(), 0);
                client.addAccounts(account);
                accountRepository.save(account);


                return new ResponseEntity<>(HttpStatus.CREATED);

        }
}
