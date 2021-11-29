package com.mindhub.homebanking;

import com.mindhub.homebanking.Models.*;
import com.mindhub.homebanking.Repositories.*;
import org.apache.tomcat.jni.Local;
import org.aspectj.weaver.tools.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class HomebankingApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {

		SpringApplication.run(HomebankingApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository loanRepository, ClientLoanRepository clientLoanRepository, CardRepository cardRepository) {
		return (args) -> {
			//codigo mhh (MindHubHomebanking)
			Client client1 = new Client("Melba", "Morel", "melba@mindhub.com", passwordEncoder.encode("melbamorel123"));
			clientRepository.save(client1);


			Loan loan1 = new Loan("Mortgage", 500000, List.of(12, 24, 36, 48, 60));
			loanRepository.save(loan1);
			Loan loan2 = new Loan("Personal", 100000, List.of(6, 12, 24));
			loanRepository.save(loan2);
			Loan loan3 = new Loan("Car", 300000, List.of(6, 12, 24, 36));
			loanRepository.save(loan3);

			ClientLoan clientLoan1 = new ClientLoan(client1, loan1, 400000, 60);
			client1.addClientLoan(clientLoan1);
			clientLoanRepository.save(clientLoan1);

			ClientLoan clientLoan2 = new ClientLoan(client1, loan2, 50000, 12);
			client1.addClientLoan(clientLoan2);
			clientLoanRepository.save(clientLoan2);

			Card card1 = new Card("Melba Morel", "1234-5678-9101-1213", "123", LocalDateTime.now(), LocalDateTime.now().plusYears(5), CardType.DEBIT, CardColor.GOLD);
			client1.addCards(card1);
			cardRepository.save(card1);

			Card card2 = new Card("Melba Morel", "1452-3698-7412-1593", "987", LocalDateTime.now(), LocalDateTime.now().plusYears(5), CardType.CREDIT, CardColor.TITANIUM);
			client1.addCards(card2);
			cardRepository.save(card2);

			Account account1 = new Account("mhh0001", LocalDateTime.now(), 5000);
			client1.addAccounts(account1);
			accountRepository.save(account1);

			Transaction transaction1 = new Transaction(2000, "income 2000 dollars", TransactionType.CREDIT, account1);
			account1.addTransactions(transaction1);
			transactionRepository.save(transaction1);

			Transaction transaction2 = new Transaction(-500, "withdraw 500 dollars", TransactionType.DEBIT, account1);
			account1.addTransactions(transaction2);
			transactionRepository.save(transaction2);

			Account account2 = new Account("mhh0002", LocalDateTime.now().plusDays(1), 7500);
			client1.addAccounts(account2);
			accountRepository.save(account2);

			transaction1 = new Transaction(3500, "income 3500 dollars", TransactionType.CREDIT, account2);
			account2.addTransactions(transaction1);
			transactionRepository.save(transaction1);

			transaction2 = new Transaction(-1500, "withdraw 1500 dollars", TransactionType.DEBIT, account2);
			account2.addTransactions(transaction2);
			transactionRepository.save(transaction2);



			Client client2 = new Client("Susana", "Gimenez", "susi.gimenez@outlook.com", passwordEncoder.encode("soysusana1900"));
			clientRepository.save(client2);

			ClientLoan clientLoan3 = new ClientLoan(client2, loan2, 100000, 24);
			client2.addClientLoan(clientLoan3);
			clientLoanRepository.save(clientLoan3);

			ClientLoan clientLoan4 = new ClientLoan(client2, loan3, 200000, 36);
			client2.addClientLoan(clientLoan4);
			clientLoanRepository.save(clientLoan4);

			Card card3 = new Card("Susana Gimenez", "6971-1532-1274-1769", "032", LocalDateTime.now(), LocalDateTime.now().plusYears(5), CardType.CREDIT, CardColor.TITANIUM);
			client2.addCards(card3);
			cardRepository.save(card3);

			Account account3 = new Account("mhh0003", LocalDateTime.now(), 25000.50);
			client2.addAccounts(account3);
			accountRepository.save(account3);

			transaction1 = new Transaction(5300, "income 5300 dollars", TransactionType.CREDIT, account3);
			account3.addTransactions(transaction1);
			transactionRepository.save(transaction1);

			transaction2 = new Transaction(-8100, "withdraw 8100 dollars", TransactionType.DEBIT, account3);
			account3.addTransactions(transaction2);
			transactionRepository.save(transaction2);

			Account account4 = new Account("mhh0004", LocalDateTime.now().plusDays(1), 34000.75);
			client2.addAccounts(account4);
			accountRepository.save(account4);

			transaction1 = new Transaction(4350, "income 4350 dollars", TransactionType.CREDIT, account4);
			account4.addTransactions(transaction1);
			transactionRepository.save(transaction1);

			transaction2 = new Transaction(-18100, "withdraw 18100 dollars", TransactionType.DEBIT, account4);
			account4.addTransactions(transaction2);
			transactionRepository.save(transaction2);



			Client client3 = new Client("Pocho", "LaPantera", "pocho.pantera@outlook.com", passwordEncoder.encode("panterafiera2021"));
			clientRepository.save(client3);

			Account account5 = new Account("mhh0005", LocalDateTime.now(), 50000.75);
			client3.addAccounts(account5);
			accountRepository.save(account5);

			transaction1 = new Transaction(7300, "income 7300 dollars", TransactionType.CREDIT, account5);
			account5.addTransactions(transaction1);
			transactionRepository.save(transaction1);

			transaction2 = new Transaction(-2100, "withdraw 2100 dollars", TransactionType.DEBIT, account5);
			account5.addTransactions(transaction2);
			transactionRepository.save(transaction2);

			Card card4 = new Card("Pocho La Pantera", "1111-2536-1478-9547", "111", LocalDateTime.now(), LocalDateTime.now().plusYears(5), CardType.CREDIT, CardColor.TITANIUM);
			client3.addCards(card4);
			cardRepository.save(card4);

			Account account6 = new Account("mhh0006", LocalDateTime.now().plusDays(1), 234000.75);
			client3.addAccounts(account6);
			accountRepository.save(account6);

			transaction1 = new Transaction(44350, "income 44350 dollars", TransactionType.CREDIT, account6);
			account6.addTransactions(transaction1);
			transactionRepository.save(transaction1);

			transaction2 = new Transaction(-78100, "withdraw 78100 dollars", TransactionType.DEBIT, account6);
			account6.addTransactions(transaction2);
			transactionRepository.save(transaction2);


			Client client4 = new Client("admin", "admin", "admin@admin.com", passwordEncoder.encode("admin123"));
			clientRepository.save(client4);
		};
	}
}
