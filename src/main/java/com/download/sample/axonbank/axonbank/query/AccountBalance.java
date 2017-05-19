package com.download.sample.axonbank.axonbank.query;

import com.download.sample.axonbank.axonbank.account.Account;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: mamiller
 * Date: 5/15/17
 * Time: 3:05 PM
 */
@Entity
public class AccountBalance {

    @Id
    private String accountId;
    private int balance;

    public AccountBalance() {

    }

    public AccountBalance(String accountId, int balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
