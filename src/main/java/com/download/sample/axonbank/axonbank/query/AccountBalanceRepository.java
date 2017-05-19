package com.download.sample.axonbank.axonbank.query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: mamiller
 * Date: 5/15/17
 * Time: 3:06 PM
 */
@Repository
public interface AccountBalanceRepository extends JpaRepository<AccountBalance, String> {
}
