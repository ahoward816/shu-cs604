package org.edu.shu;

/**
 * Created by austin on 9/30/13
 */
public class SavingsAccountTransaction extends Transaction {
    private SavingsAccountTransaction(long creditAccountId, long debitAccountId, float amount) {
        super(creditAccountId, debitAccountId, amount);
    }

    public static SavingsAccountTransaction execute(SavingsAccount account, float amount) {
        SavingsAccountTransaction trans =
                new SavingsAccountTransaction(account.getId(), Transaction.NOACCOUNT, amount);
        trans.persist();
        return trans;
    }

    public void persist() {
        // be nice and make sure we always save
        System.out.printf("Saved transaction %d to database at %s\n", this.getId(), this.getDate().toString());
        this.setStatus(Status.SUCCESS);
    }
}
