import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdatePaiementTest {

    @Test
    void shouldBePaid(){
        UpdateStatusInvoiceTest trying = new UpdateStatusInvoiceTest();

        String result = trying.createPaiment("5","1500",1);

        assertEquals("payed", result);
    }
    @Test
    void shouldBeNotPaid(){
        UpdateStatusInvoiceTest trying = new UpdateStatusInvoiceTest();

        String result = trying.createPaiment("6","0",1);

        assertEquals("not payed", result);
    }

    @Test
    void shouldBePartielle(){
        UpdateStatusInvoiceTest trying = new UpdateStatusInvoiceTest();

        String result = trying.createPaiment("6","1",1);

        assertEquals("partielle", result);
    }

}
