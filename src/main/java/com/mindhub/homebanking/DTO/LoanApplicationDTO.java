package com.mindhub.homebanking.DTO;

import com.mindhub.homebanking.Models.ClientLoan;
import com.mindhub.homebanking.Models.Account;

public class LoanApplicationDTO {

    private Long loanId;
    private double  amountRequest;
    private Integer payments;
    private String destinationAccountNumber;

    public LoanApplicationDTO() {}

    public LoanApplicationDTO(ClientLoan clientLoan, Account account) {
        this.loanId = clientLoan.getLoan().getId();
        this.amountRequest = clientLoan.getAmountRequest();
        this.payments = clientLoan.getPayments();
        this.destinationAccountNumber = account.getNumber();
    }


    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public double getAmountRequest() {
        return amountRequest;
    }

    public void setAmountRequest(double amountRequest) {
        this.amountRequest = amountRequest;
    }

    public Integer getPayments() {
        return payments;
    }

    public void setPayments(Integer payments) {
        this.payments = payments;
    }

    public String getDestinationAccountNumber() {return destinationAccountNumber;}

    public void setDestinationAccountNumber(String destinationAccountNumber) {this.destinationAccountNumber = destinationAccountNumber;}

}
