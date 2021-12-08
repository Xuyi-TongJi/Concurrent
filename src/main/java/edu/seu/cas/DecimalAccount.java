package edu.seu.cas;

import java.math.BigDecimal;

public interface DecimalAccount {

    BigDecimal getBalance();

    void withdraw(BigDecimal amount);
}
