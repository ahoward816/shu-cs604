package org.edu.shu;


import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public abstract class Transaction {
    private static final AtomicLong currentId = new AtomicLong(0);
    public static final long NOACCOUNT = -1;
    public enum Status {
        SUCCESS, FAILED, INSFUNDS, INVALID
    }

    public long getId() {
        return id;
    }

    public float getAmount() {
        return amount;
    }

    public long getDebitAccountId() {
        return debitAccountId;
    }

    public long getCreditAccountId() {
        return creditAccountId;
    }

    public Date getDate() {
        return date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    private static long generateNewId() {
        return currentId.incrementAndGet();
    }

    private long id;
    private Date date;
    private long creditAccountId;
    private long debitAccountId;
    private float amount;

    private Status status;

    public Transaction(long creditAccountId, long debitAccountId, float amount) {
        this.id = generateNewId();
        this.date = new Date();
        this.creditAccountId = creditAccountId;
        this.debitAccountId = debitAccountId;
        this.amount = amount;
        this.status = Transaction.Status.FAILED;
    }

    public void persist() {
        // save to database (very unreliable db server)
        Status[] values = Status.values();
        this.status = values[Math.abs(new Random().nextInt()) % values.length];
    }
}
