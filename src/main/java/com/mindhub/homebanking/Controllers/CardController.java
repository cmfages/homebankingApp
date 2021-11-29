package com.mindhub.homebanking.Controllers;

import com.mindhub.homebanking.Models.*;
import com.mindhub.homebanking.Repositories.CardRepository;
import com.mindhub.homebanking.Repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RepositoryRestResource
@RequestMapping("/api")
public class CardController {

    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private ClientRepository clientRepository;


    public String getCardNumber(int firstMin, int firstMax,int min, int max) {
        int random1 = (int)((Math.random()*firstMax)+firstMin);
        int random2 = (int)((Math.random()*max)+min);
        int random3 = (int)((Math.random()*max)+min);
        int random4 = (int)((Math.random()*max)+min);
        return  (getRandomToString(random1)+"-"+getRandomToString(random2)+"-"+getRandomToString(random3)+"-"+getRandomToString(random4));
        //return (random1 + "-" + random2 + "-" + random3 + "-" + random4);
    }
    public String getRandomToString(int randomN) {
        String randomNString = "";
        if (randomN <10) {
            randomNString = Integer.toString(randomN);
            randomNString = "00" + randomNString;
        } else if (randomN <100) {
            randomNString = Integer.toString(randomN);
            randomNString = "0" + randomNString;
        } else {
            randomNString = Integer.toString(randomN);
        }
        return (randomNString);
    }
    public String getCvv(int min, int max) {
        int randomN = (int)((Math.random()*max)+min);
        String randomNString = "";
        if (randomN <10) {
            randomNString = Integer.toString(randomN);
            randomNString = "00" + randomNString;
        } else if (randomN <100) {
            randomNString = Integer.toString(randomN);
            randomNString = "0" + randomNString;
        } else {
            randomNString = Integer.toString(randomN);
        }
        return (randomNString);
    }

    @RequestMapping(path = "/clients/current/cards", method = RequestMethod.POST)


    public ResponseEntity<Object> newCards(Authentication authentication,
        @RequestParam CardType cardType, @RequestParam CardColor cardColor) {
        Client client = clientRepository.findByEmail(authentication.getName());
        if (client.getCards().stream().filter(card -> card.getType().equals(cardType)).count() <= 2) {
            Card card = new Card(client.getFirstName() + " " + client.getLastName(), getCardNumber(4000, 4998, 1, 9998), getCvv(1, 998), LocalDateTime.now(), LocalDateTime.now().plusYears(5), cardType, cardColor);
            client.addCards(card);
            cardRepository.save(card);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @RequestMapping(path = "/clients/current/cards", method = RequestMethod.PUT)

    public void deleteCards(@RequestParam Long cardId) {
        Card card = cardRepository.findById(cardId).orElse(null);
        if (card != null) {
            cardRepository.delete(card);
        }
    }
}
