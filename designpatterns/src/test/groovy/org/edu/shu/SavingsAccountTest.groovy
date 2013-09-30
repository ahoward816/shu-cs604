package org.edu.shu

import spock.lang.Specification
import spock.lang.Unroll


/**
 * Created with IntelliJ IDEA.
 * User: paulsideleau
 * Date: 9/25/13
 * Time: 8:29 PM
 * To change this template use File | Settings | File Templates.
 */
class SavingsAccountTest extends Specification {


    @Unroll
    def "should add to account balance"()
    {
        given: " I have an account with balance $currentBalance"
           Account savingsAccount = new SavingsAccount(currentBalance)
        when: " I deposit my account"
            String transactionNumber = savingsAccount.deposit(depositAmount)
        then: " my balance should be higher"
          savingsAccount.balance == newBalance
          transactionNumber != null
        where:
          currentBalance | depositAmount | newBalance
           0             | 100     | 100
           5             | 25      | 30

    }
}