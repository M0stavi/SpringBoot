package com.mahin.banking.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.mahin.banking.entity.Account;
import com.mahin.banking.entity.Card;
import com.mahin.banking.exception.SuccessDetails;
import com.mahin.banking.exception.account.AccountNotFoundException;
import com.mahin.banking.exception.card.CardNotFoundException;
import com.mahin.banking.exception.card.DuplicateCardNumberException;
import com.mahin.banking.repository.AccountJpaRepository;
import com.mahin.banking.repository.CardJpaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RestController
public class CardController {

    @Autowired
    private CardJpaRepository cardJpaRepository;

    @Autowired
    private AccountJpaRepository accountJpaRepository;

    public CardController(CardJpaRepository cardJpaRepository, AccountJpaRepository accountJpaRepository) {
        this.cardJpaRepository = cardJpaRepository;
        this.accountJpaRepository = accountJpaRepository;
    }

    @PostMapping("/accounts/{accountNumber}/cards")
    public EntityModel<Card> createCard(@Valid @PathVariable Long accountNumber, @Valid @RequestBody Card card){
        for (Account account: accountJpaRepository.findAll()){
            if (account.getAccountNumber() == accountNumber){
                card.setAccount(account);
                for (Card existingCard: cardJpaRepository.findAll()){
                    if (Objects.equals(card.getCardNumber(), existingCard.getCardNumber())){
                        throw new DuplicateCardNumberException("card no: "+card.getCardNumber());
                    }
                }
                card.setName(accountJpaRepository.findByAccountNumber(accountNumber).getCustomer().getName());
//                card.setBalance(account.getBalance());
                cardJpaRepository.save(card);
                EntityModel<Card> entityModel = EntityModel.of(card);
                WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllCard(accountNumber));
                entityModel.add(linkBuilder.withRel("all-cards"));
                return entityModel;
            }
        }
        throw new AccountNotFoundException("account number: "+accountNumber);
    }

    @GetMapping("/accounts/{accountNumber}/cards")
    public MappingJacksonValue getAllCard(@Valid @PathVariable Long accountNumber){
        Account account = accountJpaRepository.findByAccountNumber(accountNumber);
        if (account == null)
            throw new AccountNotFoundException("Acc no: "+accountNumber);
        else {

            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(account.getCards());
            SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept("cvv");
            FilterProvider filterProvider = new SimpleFilterProvider().addFilter("CvvFilter", filter);
            mappingJacksonValue.setFilters(filterProvider);
            return mappingJacksonValue;
        }
    }

    @GetMapping("/accounts/{accountNumber}/cards/{cardNumber}")
    public EntityModel<Card> getCardByNumber(@Valid @PathVariable Long accountNumber, @Valid @PathVariable
                                                  Long cardNumber){
        Account account = accountJpaRepository.findByAccountNumber(accountNumber);
        if (account == null)
            throw new AccountNotFoundException("Acc no: "+accountNumber);
        else {
            Card card = cardJpaRepository.findByCardNumber(cardNumber);
            if (card == null)
                throw new CardNotFoundException("Card no: "+cardNumber);
            else {
                EntityModel<Card> entityModel = EntityModel.of(card);
                WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllCard(accountNumber));
                entityModel.add(linkBuilder.withRel("all-cards"));
                return entityModel;
            }
        }
    }

    @DeleteMapping("/accounts/{accountNumber}/cards/{cardNumber}")
    public EntityModel<SuccessDetails> deleteSpecific(@Valid @PathVariable Long accountNumber,
                                                 @Valid @PathVariable Long cardNumber){
        Account account = accountJpaRepository.findByAccountNumber(accountNumber);
        if (account == null)
            throw new AccountNotFoundException("Account no: "+accountNumber);
        else {
            Card card = cardJpaRepository.findByCardNumber(cardNumber);
            if (card == null)
                throw new CardNotFoundException("Card no: "+cardNumber);
            else {
                cardJpaRepository.delete(card);
                SuccessDetails successDetails = new SuccessDetails("Card: "+cardNumber+" deleted successfully", LocalDateTime.now());
                EntityModel<SuccessDetails> entityModel = EntityModel.of(successDetails);
                return entityModel;
            }
        }
    }

    @PutMapping("/accounts/{accountNumber}/cards/{cardNumber}")
    public EntityModel<Card> editCard(@Valid @PathVariable Long accountNumber,
                                      @Valid @PathVariable Long cardNumber, @Valid @RequestBody Card card){
        Account account = accountJpaRepository.findByAccountNumber(accountNumber);
        if (account == null)
            throw new AccountNotFoundException("Account no: "+accountNumber);
        else {
            Card retrievedCard = cardJpaRepository.findByCardNumber(cardNumber);
            if (retrievedCard == null)
                throw new CardNotFoundException("Card: "+cardNumber);
            else {
                retrievedCard.setCardNumber(card.getCardNumber());
                retrievedCard.setCardType(card.getCardType());
                retrievedCard.setCvv(card.getCvv());
                cardJpaRepository.save(retrievedCard);
                EntityModel<Card> entityModel = EntityModel.of(retrievedCard);
                WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllCard(accountNumber));
                entityModel.add(linkBuilder.withRel("all-cards"));
                return entityModel;
            }
        }
    }

    @DeleteMapping("/cards")
    public void deleteAll(){
        cardJpaRepository.deleteAll();
    }

}
