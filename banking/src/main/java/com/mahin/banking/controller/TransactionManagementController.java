package com.mahin.banking.controller;

import com.mahin.banking.entity.Account;
import com.mahin.banking.entity.Card;
import com.mahin.banking.entity.Transaction;
import com.mahin.banking.entity.enums.TransactionType;
import com.mahin.banking.exception.account.AccountNotFoundException;
import com.mahin.banking.exception.card.CardNotFoundException;
import com.mahin.banking.repository.AccountJpaRepository;
import com.mahin.banking.repository.CardJpaRepository;
import com.mahin.banking.repository.CustomerJpaRepository;
import com.mahin.banking.repository.TransactionJpaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class TransactionManagementController {
    @Autowired
    private TransactionJpaRepository transactionJpaRepository;

    @Autowired
    private AccountJpaRepository accountJpaRepository;

    @Autowired
    private CardJpaRepository cardJpaRepository;

    @Autowired
    private CustomerJpaRepository customerJpaRepository;

    @PostMapping("/transactions/accounts/{accountNumber}")
    public ResponseEntity<Object> accountTransaction(@Valid @PathVariable Long accountNumber, @Valid @RequestBody Transaction transaction){
        Account account = accountJpaRepository.findByAccountNumber(accountNumber);
        if (account == null)
            throw new AccountNotFoundException("Acc No: "+accountNumber);
        else {
            if (transaction.getTransactionType() == TransactionType.Debit){
                account.setBalance(account.getBalance()-transaction.getAmount());
            } else {
                account.setBalance(account.getBalance()+transaction.getAmount());
            }
            transaction.setAccount(account);
            transactionJpaRepository.save(transaction);
            accountJpaRepository.save(account);
            EntityModel<Account> entityModel = EntityModel.of(account);
            return new ResponseEntity<>(entityModel, HttpStatus.OK);
        }
    }

    @PostMapping("/transactions/cards/{cardNumber}")
    public ResponseEntity<Object> cardTransaction(@Valid @PathVariable Long cardNumber, @Valid @RequestBody Transaction transaction){
        Card card = cardJpaRepository.findByCardNumber(cardNumber);
        if (card == null)
            throw new CardNotFoundException("Card no: "+cardNumber);
        else {
            Account account = card.getAccount();
            if (transaction.getTransactionType() == TransactionType.Debit){
                account.setBalance(account.getBalance()-transaction.getAmount());
//                card.setBalance(card.getBalance()-transaction.getAmount());
            } else {
                account.setBalance(account.getBalance()+transaction.getAmount());
//                card.setBalance(card.getBalance()+transaction.getAmount());
            }
            transaction.setAccount(account);
            transactionJpaRepository.save(transaction);
            accountJpaRepository.save(account);
            cardJpaRepository.save(card);
            EntityModel<Card> entityModel = EntityModel.of(card);
            return new ResponseEntity<>(entityModel, HttpStatus.OK);
        }
    }
}
