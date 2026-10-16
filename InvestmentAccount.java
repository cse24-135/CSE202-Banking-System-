package bw.banking.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** Investment account: minimum opening deposit BWP 500 and 5% monthly interest. */
public class InvestmentAccount extends Account implements InterestBearing {
    private static final BigDecimal MINIMUM_OPENING_BALANCE = new BigDecimal("500.00");
    private static final BigDecimal MONTHLY_RATE = new BigDecimal("0.05");

    public InvestmentAccount(String accountNumber, BigDecimal openingBalance,
                            String branch, Customer owner) {
        super(accountNumber, validateOpeningBalance(openingBalance), branch, owner);
    }

    private static BigDecimal validateOpeningBalance(BigDecimal amount) {
        if (amount == null || amount.compareTo(MINIMUM_OPENING_BALANCE) < 0) {
            throw new IllegalArgumentException("Investment Account requires an opening deposit of at least BWP 500.00.");
        }
        return amount;
    }

    @Override
    public void withdraw(BigDecimal amount) {
        withdrawFromBalance(amount);
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
