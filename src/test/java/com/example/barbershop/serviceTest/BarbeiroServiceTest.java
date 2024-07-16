package com.example.barbershop.serviceTest;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.barbershop.repository.BarbeiroRepository;
import com.example.barbershop.service.BarbeiroService;

public class BarbeiroServiceTest {
    
   @Mock
   BarbeiroRepository barbeiroRepoditory;

   @InjectMocks
   BarbeiroService barbeiroService;



 @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }





}
