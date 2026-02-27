package org.example.dao;

import org.example.util.FileNameGenerator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FileNameGeneratorTest {

    @Test
    void testReceiptName() {
        assertEquals("recu_10.pdf",
                FileNameGenerator.receiptName(10));
    }

    @Test
    void testFactureName() {
        assertEquals("facture_6.pdf",
                FileNameGenerator.facture(6));
    }

    @Test
    void testRapportName() {
        assertEquals("rapport012026.xls",
                FileNameGenerator.rapport(1, 2026));
    }
}