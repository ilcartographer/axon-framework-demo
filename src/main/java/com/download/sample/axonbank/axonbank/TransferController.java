package com.download.sample.axonbank.axonbank;

import com.download.sample.axonbank.axonbank.coreapi.CreateAccountCommand;
import com.download.sample.axonbank.axonbank.coreapi.DepositMoneyCommand;
import com.download.sample.axonbank.axonbank.coreapi.RequestMoneyTransferCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: mamiller
 * Date: 5/15/17
 * Time: 3:00 PM
 */
@Controller
public class TransferController {

    @Autowired
    private CommandGateway commandGateway;

    @RequestMapping("/")
    @ResponseBody
    public String sendMessage() {
        commandGateway.send(new CreateAccountCommand("1234", 1000), LoggingCallback.INSTANCE);
        commandGateway.send(new CreateAccountCommand("4321", 1000), LoggingCallback.INSTANCE);



        return "OK";
    }

    @RequestMapping("/transfer/{sourceAccount}/{destinationAccount}/{amount}")
    @ResponseBody
    public String transfer(@PathVariable String sourceAccount, @PathVariable String destinationAccount, @PathVariable int amount) {
        commandGateway.send(new RequestMoneyTransferCommand(UUID.randomUUID().toString(), sourceAccount, destinationAccount, amount));
        return "OK";
    }
}
