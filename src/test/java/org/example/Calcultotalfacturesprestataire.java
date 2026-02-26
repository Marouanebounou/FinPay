package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Calcultotalfacturesprestataire {

    public double calculerTotal(List<Double> montants) {
        if (montants == null || montants.isEmpty()) {
            return 0.0;
        }
        double total = 0.0;
        for (Double m : montants) {
            if (m != null) total += m;
        }
        return total;
    }

    @Test
    @DisplayName("Tester la fonction callerTotal")
    void testCalculerTotal(){
        Calcultotalfacturesprestataire calcu = new Calcultotalfacturesprestataire();
        List<Double> list = Arrays.asList(1000.0);
        double total = calcu.calculerTotal(list);
        assertEquals(1000.0 , total , "not working");
    }
}