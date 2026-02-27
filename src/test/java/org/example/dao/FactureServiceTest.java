package org.example.dao;
import org.example.services.FactureRepository;
import org.example.services.FactureService2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FactureServiceTest {
    @Mock
    private FactureRepository repository;

    @InjectMocks
    private FactureService2 service2;

    @Test
    void testCommistionCalculating(){
        when(repository.getTotalAmount()).thenReturn(1000.0);

        double result = service2.calculateCommission();

        assertEquals(20.0 , result , 0.0001);
    }
}
