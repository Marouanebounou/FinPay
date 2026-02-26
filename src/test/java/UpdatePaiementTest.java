import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdatePaiementTest {

    @Test
    void shouldBePaid(){
        UpdateStatusInvoiceTest trying = new UpdateStatusInvoiceTest();

        String result = trying.createPaiment("1","300");

        assertEquals("payed", result);
    }

}
