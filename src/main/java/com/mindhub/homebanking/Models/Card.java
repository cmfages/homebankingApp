package com.mindhub.homebanking.Models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")

    private Long id;
    private String cardholder;
    private String cardNumber;
    private String cvv;
    private String fromDate;
    private String thruDate;
    private CardType type;
    private CardColor color;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Client user;

    public Card() {}

    public Card(String cardholder, String cardNumber, String cvv, LocalDateTime fromDate, LocalDateTime thruDate, CardType type, CardColor color) {
        this.cardholder = cardholder;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.fromDate = fromDate.format(DateTimeFormatter.ofPattern("MM-yyyy"));
        this.thruDate = thruDate.format(DateTimeFormatter.ofPattern("MM-yyyy"));
        this.type = type;
        this.color = color;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getCardholder() {
        return cardholder;
    }
    public void setCardholder(String cardholder) {
        this.cardholder = cardholder;
    }

    public String getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCvv() {
        return cvv;
    }
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getFromDate() {
        return fromDate;
    }
    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getThruDate() {
        return thruDate;
    }
    public void setThruDate(String thruDate) {
        this.thruDate = thruDate;
    }

    public CardType getType() {
        return type;
    }
    public void setType(CardType type) {
        this.type = type;
    }

    public CardColor getColor() {
        return color;
    }
    public void setColor(CardColor color) {
        this.color = color;
    }

    public Client getUser() {
        return user;
    }
    public void setUser(Client user) {
        this.user = user;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Card{");
        sb.append("id=").append(id);
        sb.append(", cardholder='").append(cardholder).append('\'');
        sb.append(", number=").append(cardNumber);
        sb.append(", cvv=").append(cvv);
        sb.append(", fromDate='").append(fromDate).append('\'');
        sb.append(", thruDate='").append(thruDate).append('\'');
        sb.append(", type=").append(type);
        sb.append(", color=").append(color);
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }
}
