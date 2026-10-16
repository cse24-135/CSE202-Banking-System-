package bw.banking.model;

import java.math.BigDecimal;

/** Defines accounts that can calculate and credit monthly interest. */
public interface InterestBearing {
    BigDecimal calculateMonthlyInterest();
    void applyMonthlyInterest();
}
