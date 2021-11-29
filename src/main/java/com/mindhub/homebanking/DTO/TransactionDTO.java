package com.mindhub.homebanking.DTO;

import com.mindhub.homebanking.Models.Transaction;
import com.mindhub.homebanking.Models.TransactionType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionDTO {
        private Long id;
        private double amount;
        private String description;
        private String date;
        private TransactionType type;

        public TransactionDTO() {}

        public TransactionDTO(Transaction transaction) {
                this.id = transaction.getId();
                this.amount = transaction.getAmount();
                this.description = transaction.getDescription();
                this.date = transaction.getDate();
                this.type = transaction.getType();
        }

        public Long getId() {
                return id;
        }

        public double getAmount() {
                return amount;
        }
        public void setAmount(double amount) {
                this.amount = amount;
        }

        public String getDescription() {
                return description;
        }
        public void setDescription(String description) {
                this.description = description;
        }

        public String getDate() {
                return date;
        }
        public void setDate(LocalDateTime date) {this.date = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));}

        public TransactionType getType() {
                return type;
        }
        public void setType(TransactionType type) {
                this.type = type;
        }


}
