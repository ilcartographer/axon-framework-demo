package com.download.sample.axonbank.axonbank.transfer;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: mamiller
 * Date: 5/15/17
 * Time: 1:55 PM
 */
@Component
public class LoggingEventHandler {

    @EventHandler
    public void on(Object event) {
        System.out.println("Event received: " + event.toString());
    }
}
