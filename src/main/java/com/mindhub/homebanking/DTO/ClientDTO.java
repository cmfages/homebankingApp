package com.mindhub.homebanking.DTO;

import com.mindhub.homebanking.Models.Client;

import javax.persistence.SecondaryTable;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

public class ClientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<AccountDTO> accounts;
    private Set<ClientLoanDTO> clientLoans;
    private Set<CardDTO> cards;


    public ClientDTO() {};

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();
        this.accounts = client.getAccounts().stream().map(AccountDTO::new).collect(toSet());
        this.clientLoans = client.getLoans().stream().map(ClientLoanDTO::new).collect(toSet());
        this.cards = client.getCards().stream().map(CardDTO::new).collect(toSet());
    }


    public Long getId() {  return id;
    }
    public void setId(Long id) { this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<AccountDTO> getAccounts() {
        return accounts;
    }
    public void setAccounts(Set<AccountDTO> accounts) {this.accounts = accounts;}

    public Set<ClientLoanDTO> getClientLoans() { return clientLoans;}
    public void setClientLoans(Set<ClientLoanDTO> clientLoans) {this.clientLoans = clientLoans;}

    public Set<CardDTO> getCards() { return cards;}
    public void setCards(Set<CardDTO> cards) {this.cards = cards;}

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ClientDTO{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", accounts=").append(accounts);
        sb.append(", clientLoans=").append(clientLoans);
        sb.append(", cards=").append(cards);
        sb.append('}');
        return sb.toString();
    }
}

