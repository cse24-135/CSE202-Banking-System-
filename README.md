# Banking System – Core Model (Java)

## Contents
- `Customer`: customer details and a collection of accounts.
- `Account`: abstract superclass shared by all account types.
- `SavingsAccount`: deposits allowed; withdrawals prohibited; monthly interest 0.05%.
- `InvestmentAccount`: deposits and withdrawals; opening balance must be at least BWP 500; monthly interest 5%.
- `ChequeAccount`: deposits and withdrawals; requires employer name and employer address.
- `InterestBearing`: interface for accounts that calculate and apply monthly interest.
- `BankingModelDemo`: simple demonstration.

## Run
Requires JDK 17 or newer. From the project root:

```bash
javac -d out src/main/java/bw/banking/model/*.java
java -cp out bw.banking.model.BankingModelDemo
```

No GUI, controller, or database code is included, in line with the assignment's Core Model stage.

## Notes
- Monetary values use `BigDecimal`, not `double`, to avoid common floating-point rounding problems.
- The assignment says Savings Account cannot make withdrawals; this implementation rejects them.
- Interest is calculated and applied only when `applyMonthlyInterest()` is called. A later controller/service layer can decide when monthly interest is paid.
- The interview record and documentation in the accompanying report are a mock draft. Replace it with the actual interview details before submission.
