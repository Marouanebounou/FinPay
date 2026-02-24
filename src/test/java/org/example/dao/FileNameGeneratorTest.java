package org.example.dao;

import org.example.util.FileNameGenerator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FileNameGeneratorTest {

    @Test
    void testReceiptName() {
        assertEquals("recu_10.pdf",
                FileNameGenerator.receipt(10));
    }

    @Test
    void testFactureName() {
        assertEquals("facture_5.pdf",
                FileNameGenerator.facture(5));
    }

    @Test
    void testRapportName() {
        assertEquals("rapport012026.xls",
                FileNameGenerator.rapport(1, 2026));
    }
}