package bw.banking.model;

import java.math.BigDecimal;

/** Cheque account for employed customers; stores employer details. */
public class ChequeAccount extends Account {
    private final String employerName;
    private final String employerAddress;

    public ChequeAccount(String accountNumber, BigDecimal openingBalance, String branch,
                         Customer owner, String employerName, String employerAddress) {
        super(accountNumber, openingBalance, branch, owner);
        if (employerName == null || employerName.isBlank()) {
            throw new IllegalArgumentException("Employer/company name is required for a Cheque Account.");
        }
        if (employerAddress == null || employerAddress.isBlank()) {
            throw new IllegalArgumentException("Employer/company address is required for a Cheque Account.");
        }
        this.employerName = employerName;
        this.employerAddress = employerAddress;
    }

    public String getEmployerName() { return employerName; }
    public String getEmployerAddress() { return employerAddress; }

    @Override
    public void withdraw(BigDecimal amount) {
        withdrawFromBalance(amount);
    }
}
