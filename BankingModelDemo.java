package bw.banking.model;

import java.math.BigDecimal;

/** Small console demo for checking the core model without a GUI or database. */
public class BankingModelDemo {
    public static void main(String[] args) {
        Customer customer = new Customer("C001", "Thato", "Molefe", "Gaborone");

        SavingsAccount savings = new SavingsAccount(
                "S001", new BigDecimal("1000.00"), "Gaborone Main", customer);
        InvestmentAccount investment = new InvestmentAccount(
                "I001", new BigDecimal("500.00"), "Gaborone Main", customer);
        ChequeAccount cheque = new ChequeAccount(
                "Q001", new BigDecimal("200.00"), "Gaborone Main", customer,
                "Example Company", "Plot 123, Gaborone");

        customer.addAccount(savings);
        customer.addAccount(investment);
        customer.addAccount(cheque);

        investment.deposit(new BigDecimal("100.00"));
        cheque.withdraw(new BigDecimal("50.00"));
        savings.applyMonthlyInterest();
        investment.applyMonthlyInterest();

        System.out.println(customer);
        for (Account account : customer.getAccounts()) {
            System.out.println(account + " | owner=" + account.getOwner().getFirstName());
        }
        System.out.println("Savings interest applied; investment interest applied.");
    }
}
