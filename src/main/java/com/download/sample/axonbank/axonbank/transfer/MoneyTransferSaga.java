package com.download.sample.axonbank.axonbank.transfer;

import com.download.sample.axonbank.axonbank.LoggingCallback;
import com.download.sample.axonbank.axonbank.coreapi.*;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.saga.EndSaga;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.SagaLifecycle;
import org.axonframework.eventhandling.saga.StartSaga;
import org.springframework.beans.factory.annotation.Autowired;
import org.axonframework.spring.stereotype.Saga;

import java.util.UUID;

import static org.axonframework.eventhandling.saga.SagaLifecycle.end;

/**
 * Created with IntelliJ IDEA.
 * User: mamiller
 * Date: 5/15/17
 * Time: 10:18 AM
 */

@Saga
public class MoneyTransferSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    private String targetAccount;
    private String transferId;

    @StartSaga
    @SagaEventHandler(associationProperty = "transferId")
    public void on(MoneyTransferRequestedEvent event) {
        targetAccount = event.getTargetAccount();
        transferId = event.getTransferId();
        commandGateway.send(new WithdrawMoneyCommand(event.getSourceAccount(), event.getTransferId(), event.getAmount()),
                new CommandCallback<WithdrawMoneyCommand, Object>() {
                    @Override
                    public void onSuccess(CommandMessage<? extends WithdrawMoneyCommand> commandMessage, Object o) {
                    }

                    @Override
                    public void onFailure(CommandMessage<? extends WithdrawMoneyCommand> commandMessage, Throwable throwable) {
                        commandGateway.send(new CancelMoneyTransferCommand(event.getTransferId()));
                    }
                });
    }

    @SagaEventHandler(associationProperty = "transactionId", keyName = "transferId")
    public void on(MoneyWithdrawnEvent event) {
        commandGateway.send(new DepositMoneyCommand(targetAccount, event.getTransactionId(), event.getAmount()), LoggingCallback.INSTANCE);
    }

    @SagaEventHandler(associationProperty = "transactionId", keyName = "transferId")
    public void on(MoneyDepositedEvent event) {
        commandGateway.send(new CompleteMoneyTransferCommand(transferId), LoggingCallback.INSTANCE);
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "transferId")
    public void on(MoneyTransferCompletedEvent event) {

    }

    @SagaEventHandler(associationProperty = "transferId")
    public void on(MoneyTransferCancelledEvent event) {
        end();
    }
}
