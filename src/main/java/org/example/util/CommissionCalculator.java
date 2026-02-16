package org.example.util;

import java.math.BigDecimal;

public class CommissionCalculator {

    public static BigDecimal calculerCommission(BigDecimal montant) {
        if (montant == null || montant.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal pourcentage = new BigDecimal("0.02");
        return montant.multiply(pourcentage);
    }
}

