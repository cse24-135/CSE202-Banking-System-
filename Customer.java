package bw.banking.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Represents a bank customer who may hold multiple accounts. */
public class Customer {
    private final String customerId;
    private String firstName;
    private String surname;
    private String address;
    private final List<Account> accounts = new ArrayList<>();

    public Customer(String customerId, String firstName, String surname, String address) {
        if (customerId == null || customerId.isBlank()) {
            throw new IllegalArgumentException("Customer ID is required.");
        }
        this.customerId = customerId;
        setFirstName(firstName);
        setSurname(surname);
        setAddress(address);
    }

    public String getCustomerId() { return customerId; }
    public String getFirstName() { return firstName; }
    public String getSurname() { return surname; }
    public String getAddress() { return address; }
    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isBlank()) throw new IllegalArgumentException("First name is required.");
        this.firstName = firstName;
    }
    public void setSurname(String surname) {
        if (surname == null || surname.isBlank()) throw new IllegalArgumentException("Surname is required.");
        this.surname = surname;
    }
    public void setAddress(String address) {
        if (address == null || address.isBlank()) throw new IllegalArgumentException("Address is required.");
        this.address = address;
    }

    /** Register a newly created account with this customer. */
    public void addAccount(Account account) {
        if (account == null) throw new IllegalArgumentException("Account is required.");
        if (account.getOwner() != this) {
            throw new IllegalArgumentException("Account belongs to a different customer.");
        }
        if (accounts.stream().anyMatch(a -> a.getAccountNumber().equals(account.getAccountNumber()))) {
            throw new IllegalArgumentException("Duplicate account number for this customer.");
        }
        accounts.add(account);
    }

    @Override
    public String toString() {
        return "Customer{customerId='" + customerId + "', name='" + firstName + " "
                + surname + "', accounts=" + accounts.size() + "}";
    }
}
