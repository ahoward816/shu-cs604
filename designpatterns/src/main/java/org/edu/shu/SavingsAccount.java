package org.edu.shu;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created with IntelliJ IDEA.
 * User: paulsideleau
 * Date: 9/25/13
 * Time: 8:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class SavingsAccount implements Account {
    private static final AtomicLong currentId = new AtomicLong(0);

    private long id;
    private float balance;

    public SavingsAccount(float balance) {
        this.id = generateNewId();
        this.balance = balance;
    }

    public float getBalance() {
        return balance;
    }

    public long getId() {
        return id;
    }

    @Override
    public String deposit(float amount) {
        SavingsAccountTransaction trans = SavingsAccountTransaction.execute(this, amount);
        if (trans.getStatus() == Transaction.Status.SUCCESS) {
            this.balance += amount;
            return Long.toString(trans.getId());
        }
        return "Transaction failed";
    }

    @Override
    public String withdraw(float amount) {
        if (this.balance < amount) {
            return "Insufficient funds";
        }
        SavingsAccountTransaction trans = SavingsAccountTransaction.execute(this, amount * -1);
        if (trans.getStatus() == Transaction.Status.SUCCESS) {
            this.balance -= amount;
            return Long.toString(trans.getId());
        }
        return "Transaction failed";
    }

    private static long generateNewId() {
        return currentId.incrementAndGet();
    }
}
