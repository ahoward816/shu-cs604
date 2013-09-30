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
          savingsAccount.getBalance() == newBalance
          transactionNumber != null
        where:
          currentBalance | depositAmount | newBalance
           0             | 100     | 100
           5             | 25      | 30

    }

    @Unroll
    def "should subtract from account balance"() {
        given: " I have an account with balance $currentBalance"
            Account savingsAccount = new SavingsAccount(currentBalance)
        when: " I withdraw money from my account"
            String transactionNumber = savingsAccount.withdraw(withdrawal)
        then: " my balance should be lower"
            savingsAccount.getBalance() == newBalance
            transactionNumber != null
        where:
            currentBalance | withdrawal | newBalance
             0             | 100        | 0
             2500          | 120        | 2380
    }

    @Unroll
    def "should deny my withdrawal"() {
        given: " I have an account with balance $currentBalance"
            Account savingsAccount = new SavingsAccount(currentBalance)
        when: " I try to withdraw money from my account"
            String transactionNumber = savingsAccount.withdraw(withdrawal)
        then: " I should be prevented due to insufficient funds"
            savingsAccount.getBalance() == currentBalance
            transactionNumber.equals("Insufficient funds")
        where:
            currentBalance | withdrawal | newBalance
             0             | 100        | 0
    }
}