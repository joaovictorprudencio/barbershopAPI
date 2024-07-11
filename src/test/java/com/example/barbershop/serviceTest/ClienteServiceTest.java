package com.example.barbershop.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.barbershop.entitys.Cliente;
import com.example.barbershop.repository.ClienteRepository;
import com.example.barbershop.service.ClienteService;

public class ClienteServiceTest {
     @Mock
	 ClienteRepository clienteRepository;

    @InjectMocks
     ClienteService clienteService;
     
	 @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
	 

    @Test
    void CriarCliente(){
    Cliente NovoCliente = new Cliente();
	  NovoCliente.setId((long)1);
	  NovoCliente.setNome("joao");
	  NovoCliente.setNumeroCelular("85997330821");
	  NovoCliente.setSenha("138ndfwoncuehf");

	 when(clienteRepository.save(any(Cliente.class))).thenReturn(NovoCliente);

       Cliente clienteSalvo = clienteService.CriarCliente(NovoCliente);
       assertEquals("joao", clienteSalvo.getNome());
	}

    


}
