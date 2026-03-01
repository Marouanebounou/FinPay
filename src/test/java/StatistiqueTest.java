import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatistiqueTest {
    @Test
    void normalAmount() {
        BigDecimal value = new BigDecimal("100");

        Date date = new Date();

        Statistique first = new Statistique(value, 1, date);

        BigDecimal expect = new BigDecimal("2.00");

        assertEquals(expect, first.getTotalComission());
    }

    @Test
    void nilAmount() {
        BigDecimal value = new BigDecimal("0");

        Date date = new Date();

        Statistique first = new Statistique(value, 1, date);

        BigDecimal expect = new BigDecimal("0.00");

        assertEquals(expect, first.getTotalComission());
    }

    @Test
    void bigAmount() {
        BigDecimal value = new BigDecimal("1048576");

        Date date = new Date();

        Statistique first = new Statistique(value, 1, date);

        BigDecimal expect = new BigDecimal("20971.52");

        assertEquals(0, expect.compareTo(first.getTotalComission()));
    }
}
