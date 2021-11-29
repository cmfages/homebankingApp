package com.mindhub.homebanking.Models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Entity

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")

    private Long id;
    private String number;
    private String createDate;
    private double balance;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Client user;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    Set<Transaction> transactions = new HashSet<>();

    public Set<Transaction> getTransactions() {
        return transactions;
    }


    public Account() {}

    public Account(String number, LocalDateTime createDate, double  balance) {
        this.number = number;
        this.createDate = createDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        this.balance = balance;
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
    public void setCreateDate(LocalDateTime createDate) {this.createDate = createDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));}

    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Client getUser() {
        return user;
    }
    public void setUser(Client user) {
        this.user = user;
    }

    public void addTransactions(Transaction transaction) {
        transaction.setAccount(this);
        transactions.add(transaction);
    }
}
