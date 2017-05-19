package com.download.sample.axonbank.axonbank.coreapi

import org.axonframework.commandhandling.TargetAggregateIdentifier

/**
 * Created with IntelliJ IDEA.
 * User: mamiller
 * Date: 5/15/17
 * Time: 10:15 AM
 */

class RequestMoneyTransferCommand(@TargetAggregateIdentifier val transferId: String, val sourceAccount: String, val targetAccount: String, val amount: Int)
class MoneyTransferRequestedEvent(val transferId: String, val sourceAccount: String, val targetAccount: String, val amount: Int)

class CompleteMoneyTransferCommand(@TargetAggregateIdentifier val transferId: String)
class CancelMoneyTransferCommand(@TargetAggregateIdentifier val transferId: String)

class MoneyTransferCompletedEvent(val transferId: String)
class MoneyTransferCancelledEvent(val transferId: String)