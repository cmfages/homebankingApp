package com.mindhub.homebanking.DTO;

import com.mindhub.homebanking.Models.Account;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class AccountDTO {
    private Long id;
    private String number;
    private String createDate;
    private double balance;
    private Set<TransactionDTO> transactions;

    public AccountDTO() {}

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.number = account.getNumber();
        this.createDate = account.getCreateDate();
        this.balance = account.getBalance();
        this.transactions = account.getTransactions().stream().map(TransactionDTO::new).collect(toSet());
    }
    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public String getCreateDate() {
        return createDate;
    }
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Set<TransactionDTO> getTransactions() {
        return transactions;
    }
    public void setTransactions(Set<TransactionDTO> transactions) {this.transactions = transactions;}

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AccountDTO{");
        sb.append("id=").append(id);
        sb.append(", number='").append(number).append('\'');
        sb.append(", createDate='").append(createDate).append('\'');
        sb.append(", balance=").append(balance);
        sb.append(", transactions=").append(transactions);
        sb.append('}');
        return sb.toString();
    }
}
