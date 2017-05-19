package com.download.sample.axonbank.axonbank.coreapi

import org.axonframework.commandhandling.TargetAggregateIdentifier

/**
 * Created with IntelliJ IDEA.
 * User: mamiller
 * Date: 5/15/17
 * Time: 9:09 AM
 */

class CreateAccountCommand(@TargetAggregateIdentifier val accountId: String, val overdraftLimit: Int)
class WithdrawMoneyCommand(@TargetAggregateIdentifier val accountId: String, val transactionId: String, val amount: Int)
class DepositMoneyCommand(@TargetAggregateIdentifier val accountId: String, val transactionId: String, val amount: Int)

class AccountCreatedEvent(val accountId: String, val overdraftLimit: Int)

abstract class BalanceUpdateEvent(val accountId: String, val balance: Int)
class MoneyWithdrawnEvent(accountId: String, val transactionId: String, val amount: Int, balance: Int) : BalanceUpdateEvent(accountId, balance)
class MoneyDepositedEvent(accountId: String, val transactionId: String, val amount: Int, balance: Int) : BalanceUpdateEvent(accountId, balance)

class OverdraftLimitExceededException() : Exception ()