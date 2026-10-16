package bw.banking.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** Savings account: pays 0.05% monthly interest and does not allow withdrawals. */
public class SavingsAccount extends Account implements InterestBearing {
    private static final BigDecimal MONTHLY_RATE = new BigDecimal("0.0005");

    public SavingsAccount(String accountNumber, BigDecimal openingBalance,
                          String branch, Customer owner) {
        super(accountNumber, openingBalance, branch, owner);
    }

    @Override
    public void withdraw(BigDecimal amount) {
        throw new UnsupportedOperationException("Withdrawals are not allowed from a Savings Account.");
    }

    @Override
    public BigDecimal calculateMonthlyInterest() {
        return getBalance().multiply(MONTHLY_RATE).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public void applyMonthlyInterest() {
        credit(calculateMonthlyInterest());
    }
}
