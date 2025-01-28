package com.example.barbershop.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
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
	  NovoCliente.setTelefone("85997330821");
	  NovoCliente.setSenha("138ndfwoncuehf");

     when(clienteRepository.findByNome("joao")).thenReturn(Optional.empty());

	 when(clienteRepository.save(NovoCliente)).thenReturn(NovoCliente);

       Cliente clienteSalvo = clienteService.CriarCliente(NovoCliente);
       assertNotNull(clienteSalvo);
       assertEquals("joao", clienteSalvo.getNome());
       verify(clienteRepository, times(1)).findByNome("joao");
       verify(clienteRepository, times(1)).save(clienteSalvo);
	}

    @Test
    void
    BuscarCliente(){
        Cliente NovoCliente = new Cliente();
        NovoCliente.setId((long)1);
        NovoCliente.setNome("joao");
        NovoCliente.setTelefone("85997330821");
        NovoCliente.setSenha("138ndfwoncuehf");

        when(clienteRepository.findByNome(anyString())).thenReturn(Optional.of(NovoCliente));
        
        Optional<Cliente> clienteTal = clienteService.BuscarCliente(NovoCliente);

       Cliente  clienteEncontrado = clienteTal.get() ;

        assertEquals("joao", clienteEncontrado.getNome());

    }

    @Test
    void Atualizar(){

        Cliente AntigoCliente = new Cliente();
        AntigoCliente.setId((long)1);
        AntigoCliente.setNome("joao");
        AntigoCliente.setTelefone("85997330821");
        AntigoCliente.setSenha("138ndfwoncuehf");

        Cliente NovoCliente = new Cliente();
        NovoCliente.setId((long)2);
        NovoCliente.setNome("joao victor alves prudencio");
        NovoCliente.setTelefone("859000-00");
        NovoCliente.setSenha("root123");


        when(clienteRepository.findByNome(anyString())).thenReturn(Optional.of(AntigoCliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(NovoCliente);
       

        Optional<Cliente> ClienteOptinal = Optional.ofNullable(clienteService.AtualizarCliente(NovoCliente));

        Cliente clienteAtualizado = ClienteOptinal.get();

        assertEquals("joao victor alves prudencio" , clienteAtualizado.getNome());
    }

    @Test
    void Deletar (){
        Cliente NovoCliente = new Cliente();
        NovoCliente.setId((long)2);
        NovoCliente.setNome("joao victor alves prudencio");
        NovoCliente.setTelefone("859000-00");
        NovoCliente.setSenha("root123");

        when(clienteRepository.findByNome(anyString())).thenReturn(Optional.of(NovoCliente));

         doNothing().when(clienteRepository).deleteById(NovoCliente.getId());

         clienteService.DeletarCliente(NovoCliente);
         

         verify(clienteRepository).deleteById(NovoCliente.getId());

    }

}
