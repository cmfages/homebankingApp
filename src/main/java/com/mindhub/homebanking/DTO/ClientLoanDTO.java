package com.mindhub.homebanking.DTO;

import com.mindhub.homebanking.Models.ClientLoan;


public class ClientLoanDTO {
    private Long id;
    private Long loanId;
    private String loanName;
    private double  amountRequest;
    private int payments;

    public ClientLoanDTO() {}

    public ClientLoanDTO(ClientLoan clientLoan) {
        this.id = clientLoan.getId();
        this.loanId = clientLoan.getLoan().getId();
        this.loanName = clientLoan.getLoan().getName();
        this.amountRequest = clientLoan.getAmountRequest();
        this.payments = clientLoan.getPayments();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public String getLoanName() {
        return loanName;
    }

    public void setLoanName(String loanName) {
        this.loanName = loanName;
    }

    public double getAmountRequest() {
        return amountRequest;
    }

    public void setAmountRequest(double amountRequest) {
        this.amountRequest = amountRequest;
    }

    public int getPayments() {
        return payments;
    }

    public void setPayments(int payments) {
        this.payments = payments;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ClientLoanDTO{");
        sb.append("id=").append(id);
        sb.append(", loanId=").append(loanId);
        sb.append(", loanName='").append(loanName).append('\'');
        sb.append(", amountRequest=").append(amountRequest);
        sb.append(", payments=").append(payments);
        sb.append('}');
        return sb.toString();
    }
}
