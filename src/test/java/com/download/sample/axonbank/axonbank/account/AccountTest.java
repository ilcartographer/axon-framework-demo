package com.download.sample.axonbank.axonbank.account;

import com.download.sample.axonbank.axonbank.coreapi.*;
import junit.framework.TestCase;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: mamiller
 * Date: 5/15/17
 * Time: 9:14 AM
 */
public class AccountTest extends TestCase {
    private FixtureConfiguration fixture;

    public void setUp() throws Exception {
        fixture = new AggregateTestFixture(Account.class);

    }

    @Test
    public void testCreateAccount() throws Exception {
        fixture.givenNoPriorActivity()
                .when(new CreateAccountCommand("1234", 1000))
                .expectEvents(new AccountCreatedEvent("1234", 1000));
    }

    @Test
    public void testWithdrawReasonableAmount() {
        fixture.given(new AccountCreatedEvent("1234", 1000))
                .when(new WithdrawMoneyCommand("1234", "tx1", 600))
                .expectEvents(new MoneyWithdrawnEvent("1234", "tx1", 600, -600));
    }

    @Test
    public void testWithdrawAbsurdAmount() {
        fixture.given(new AccountCreatedEvent("1234", 1000))
                .when(new WithdrawMoneyCommand("1234", "tx1", 1001))
                .expectNoEvents()
                .expectException(OverdraftLimitExceededException.class);
    }

    @Test
    public void testWithdrawTwice() {
        fixture.given(new AccountCreatedEvent("1234", 1000),
                      new MoneyWithdrawnEvent("1234", "tx1", 999, -999))
                .when(new WithdrawMoneyCommand("1234", "tx1", 2))
                .expectNoEvents()
                .expectException(OverdraftLimitExceededException.class);
    }
}