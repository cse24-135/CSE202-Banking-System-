package bw.banking.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Abstract base class for all bank accounts.
 * An account is created with an owner, so it cannot exist without a Customer.
 */
public abstract class Account {
    private final String accountNumber;
    private BigDecimal balance;
    private final String branch;
    private final Customer owner;

    protected Account(String accountNumber, BigDecimal openingBalance,
                      String branch, Customer owner) {
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("Account number is required.");
        }
        if (openingBalance == null || openingBalance.signum() < 0) {
            throw new IllegalArgumentException("Opening balance cannot be negative.");
        }
        if (branch == null || branch.isBlank()) {
            throw new IllegalArgumentException("Branch is required.");
        }
        this.owner = Objects.requireNonNull(owner, "An account must have a customer.");
        this.accountNumber = accountNumber;
        this.balance = openingBalance;
        this.branch = branch;
    }

    public String getAccountNumber() { return accountNumber; }
    public BigDecimal getBalance() { return balance; }
    public String getBranch() { return branch; }
    public Customer getOwner() { return owner; }

    public void deposit(BigDecimal amount) {
        validatePositiveAmount(amount);
        balance = balance.add(amount);
    }

    /** Account types implement their own withdrawal rules. */
    public abstract void withdraw(BigDecimal amount);

    protected void withdrawFromBalance(BigDecimal amount) {
        validatePositiveAmount(amount);
        if (balance.compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient funds.");
        }
        balance = balance.subtract(amount);
    }

    protected void credit(BigDecimal amount) {
        validatePositiveAmount(amount);
        balance = balance.add(amount);
    }

    protected void validatePositiveAmount(BigDecimal amount) {
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{accountNumber='" + accountNumber
                + "', balance=" + balance + ", branch='" + branch + "'}";
    }
}
