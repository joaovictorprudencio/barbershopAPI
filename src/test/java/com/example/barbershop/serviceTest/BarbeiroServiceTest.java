package com.example.barbershop.serviceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.barbershop.entitys.Barbeiro;
import com.example.barbershop.repository.BarbeiroRepository;
import com.example.barbershop.service.BarbeiroService;

public class BarbeiroServiceTest {
    
   @Mock
   BarbeiroRepository barbeiroRepository;

   @InjectMocks
   BarbeiroService barbeiroService;



 @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void CriarBarbeiro(){
       Barbeiro barbeiro = new Barbeiro();
       barbeiro.setId((long)1);
       barbeiro.setNome("maikon");
       barbeiro.setNumeroCelular("85998542232");


       when(barbeiroRepository.findByNome("maikon")).thenReturn(Optional.empty());

       when(barbeiroRepository.save(barbeiro)).thenReturn(barbeiro);

       Barbeiro NovoBarbeiro = barbeiroService.CriarBarbeiro(barbeiro);

       assertNotNull(NovoBarbeiro);
       assertEquals("maikon" , NovoBarbeiro.getNome());
       verify(barbeiroRepository, times(1)).findByNome("maikon");
       verify(barbeiroRepository, times(1)).save(NovoBarbeiro);

    }


    @Test
   void BuscarBarbeiro(){
       Barbeiro instancia = new Barbeiro();
       instancia.setId((long)1);
       instancia.setNome("maikon");
       instancia.setNumeroCelular("85998542232");


       when(barbeiroRepository.findByNome("maikon")).thenReturn(Optional.of(instancia));
       Optional<Barbeiro> barbeiroOptional = barbeiroService.BuscarBarbeiro(instancia);


       assertTrue(barbeiroOptional.isPresent());
       Barbeiro barbeiro = barbeiroOptional.get();
       assertEquals("maikon", barbeiro.getNome());
       assertEquals("85998542232", barbeiro.getNumeroCelular());
    }






}
