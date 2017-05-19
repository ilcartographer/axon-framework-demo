package com.download.sample.axonbank.axonbank;

import com.download.sample.axonbank.axonbank.account.Account;
import com.download.sample.axonbank.axonbank.coreapi.CreateAccountCommand;
import com.download.sample.axonbank.axonbank.coreapi.RequestMoneyTransferCommand;
import com.download.sample.axonbank.axonbank.query.AccountBalanceRepository;
import com.download.sample.axonbank.axonbank.transfer.LoggingEventHandler;
import com.download.sample.axonbank.axonbank.transfer.MoneyTransfer;
import com.download.sample.axonbank.axonbank.transfer.MoneyTransferSaga;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.config.Configuration;
import org.axonframework.config.DefaultConfigurer;
import org.axonframework.config.EventHandlingConfiguration;
import org.axonframework.config.SagaConfiguration;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;

import java.util.concurrent.ExecutionException;


/**
 * Created with IntelliJ IDEA.
 * User: mamiller
 * Date: 5/15/17
 * Time: 9:35 AM
 */
public class Application {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Configuration configuration = DefaultConfigurer.defaultConfiguration()
                .configureAggregate(Account.class)
                .configureAggregate(MoneyTransfer.class)
                .registerModule(SagaConfiguration.subscribingSagaManager(MoneyTransferSaga.class))
                .registerModule(new EventHandlingConfiguration()
                        .registerEventHandler(c -> new LoggingEventHandler()))
                .configureEmbeddedEventStore(c -> new InMemoryEventStorageEngine())
                .buildConfiguration();

        configuration.start();

        CommandGateway commandGateway = configuration.commandGateway();

        commandGateway.send(new CreateAccountCommand("1234", 1000), LoggingCallback.INSTANCE);
        commandGateway.send(new CreateAccountCommand("4321", 1000), LoggingCallback.INSTANCE);
        commandGateway.send(new RequestMoneyTransferCommand("tf1", "1234", "4321", 100), LoggingCallback.INSTANCE);

        configuration.shutdown();
    }
}
