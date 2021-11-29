package com.mindhub.homebanking.DTO;

import com.mindhub.homebanking.Models.Card;
import com.mindhub.homebanking.Models.CardColor;
import com.mindhub.homebanking.Models.CardType;
import com.mindhub.homebanking.Models.TransactionType;

public class CardDTO {

    private Long id;
    private String cardholder;
    private String cardNumber;
    private String cvv;
    private String fromDate;
    private String thruDate;
    private CardType type;
    private CardColor color;

    public CardDTO() {}

    public CardDTO(Card card) {
        this.id = card.getId();
        this.cardholder = card.getCardholder();
        this.cardNumber = card.getCardNumber();
        this.cvv = card.getCvv();
        this.fromDate = card.getFromDate();
        this.thruDate = card.getThruDate();
        this.type = card.getType();
        this.color = card.getColor();
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CardDTO{");
        sb.append("id=").append(id);
        sb.append(", cardholder='").append(cardholder).append('\'');
        sb.append(", cardNumber='").append(cardNumber).append('\'');
        sb.append(", cvv='").append(cvv).append('\'');
        sb.append(", fromDate='").append(fromDate).append('\'');
        sb.append(", thruDate='").append(thruDate).append('\'');
        sb.append(", type=").append(type);
        sb.append(", color=").append(color);
        sb.append('}');
        return sb.toString();
    }
}
