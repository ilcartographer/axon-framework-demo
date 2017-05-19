package com.download.sample.axonbank.axonbank.account;

import com.download.sample.axonbank.axonbank.coreapi.*;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.Basic;
import javax.persistence.Id;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

/**
 * Created with IntelliJ IDEA.
 * User: mamiller
 * Date: 5/15/17
 * Time: 9:12 AM
 */
@Aggregate
@NoArgsConstructor
public class Account {

    @Id
    @AggregateIdentifier
    private String accountId;

    @Basic
    private int balance;

    @Basic
    private int overdraftLimit;

    @CommandHandler
    public Account(CreateAccountCommand command) {
        apply(new AccountCreatedEvent(command.getAccountId(), command.getOverdraftLimit()));
    }

    @CommandHandler
    public void handle(WithdrawMoneyCommand command) throws OverdraftLimitExceededException {
        if (balance + overdraftLimit >= command.getAmount()) {
            apply(new MoneyWithdrawnEvent(accountId, command.getTransactionId(), command.getAmount(), balance - command.getAmount()));
        } else {
            throw new OverdraftLimitExceededException();
        }
    }

    @CommandHandler
    public void handle(DepositMoneyCommand command) {
        apply(new MoneyDepositedEvent(accountId, command.getTransactionId(), command.getAmount(), balance + command.getAmount()));
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event) {
        this.accountId = event.getAccountId();
        this.overdraftLimit = event.getOverdraftLimit();
    }

    @EventSourcingHandler
    public void on(MoneyWithdrawnEvent event) {
        this.balance = event.getBalance();
    }

    @EventSourcingHandler
    public void on(MoneyDepositedEvent event) { this.balance = event.getBalance(); }
}
