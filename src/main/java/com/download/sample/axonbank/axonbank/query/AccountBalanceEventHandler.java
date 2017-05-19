package com.download.sample.axonbank.axonbank.query;

import com.download.sample.axonbank.axonbank.coreapi.AccountCreatedEvent;
import com.download.sample.axonbank.axonbank.coreapi.BalanceUpdateEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: mamiller
 * Date: 5/15/17
 * Time: 3:04 PM
 */
@RestController
public class AccountBalanceEventHandler {

    private final AccountBalanceRepository repository;

    public AccountBalanceEventHandler(AccountBalanceRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(BalanceUpdateEvent event) {
        repository.save(new AccountBalance(event.getAccountId(), event.getBalance()));
    }

    @EventHandler
    public void on(AccountCreatedEvent event) {
        repository.save(new AccountBalance(event.getAccountId(), 0));
    }

    @GetMapping("/balance/{id}")
    public AccountBalance getBalance(@PathVariable String id) {
        return repository.findOne(id);
    }

}
