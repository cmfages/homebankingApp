package com.mindhub.homebanking;

import com.mindhub.homebanking.Models.*;
import com.mindhub.homebanking.Repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
//@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoriesTest {

    @Autowired
    LoanRepository loanRepository;

    @Test
    public void existLoans() {
        List<Loan> loans = loanRepository.findAll();
        assertThat(loans,is(not(empty())));
    }
    @Test
    public void existPersonalLoan(){
        List<Loan> loans = loanRepository.findAll();
        assertThat(loans,hasItem(hasProperty("name",is("Personal"))));
    }


    @Autowired
    TransactionRepository transactionRepository;

    @Test
    public void existTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        assertThat(transactions, is(not(empty())));
    }
    @Test
    public void existTransaction() {
        List<Transaction> transactions = transactionRepository.findAll();
        assertThat(transactions, hasItem(hasProperty("amount")));
    }


    @Autowired
    AccountRepository accountRepository;

    @Test
    public void existAccounts() {
        List<Account> accounts = accountRepository.findAll();
        assertThat(accounts, is(not(empty())));
    }
    @Test
    public void existAccount() {
        List<Account> accounts = accountRepository.findAll();
        assertThat(accounts, hasItem(hasProperty("number")));
        assertThat(accounts, hasItem(hasProperty("balance")));
    }


    @Autowired
    CardRepository cardRepository;

    @Test
    public void existCards() {
        List<Card> cards = cardRepository.findAll();
        assertThat(cards, is(not(empty())));
    }
    @Test
    public void existCard() {
        List<Card> cards = cardRepository.findAll();
        assertThat(cards, hasItem(hasProperty("cardholder")));
        assertThat(cards, hasItem(hasProperty("cardNumber")));
        assertThat(cards, hasItem(hasProperty("cvv")));
    }

    @Autowired
    ClientRepository clientRepository;

    @Test
    public void existClients() {
        List<Client> clients = clientRepository.findAll();
        assertThat(clients, is(not(empty())));
    }
    @Test
    public void existClient() {
        List<Client> clients = clientRepository.findAll();
        assertThat(clients, hasItem(hasProperty("firstName")));
        assertThat(clients, hasItem(hasProperty("lastName")));
        assertThat(clients, hasItem(hasProperty("email")));
    }

}