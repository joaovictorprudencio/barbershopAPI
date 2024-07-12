package com.example.barbershop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.barbershop.entitys.Cliente;
import com.example.barbershop.repository.ClienteRepository;


@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;



    public Cliente CriarCliente(Cliente cliente){
      Optional<Cliente> ClienteExistente = clienteRepository.findByNome(cliente.getNome());
       
      if(ClienteExistente.isPresent()){
        throw new ClienteException("O nome de usuário já está em uso: " + cliente.getNome());
      }
        return clienteRepository.save(cliente);
    }

    
    public Optional<Cliente> BuscarCliente(Cliente cliente) {
         return clienteRepository.findByNome(cliente.getNome());
    }

   public void DeletarCliente(Cliente cliente ){
        Optional<Cliente> clienteOptinal = clienteRepository.findByNome(cliente.getNome());

        Cliente ClienteExistente = clienteOptinal.get();

        clienteRepository.deleteById(ClienteExistente.getId());
   }

   public Optional<Cliente> AtualizarCliente (Cliente cliente){
       Optional<Cliente> clienteBusca = clienteRepository.findByNome(cliente.getNome());

       if(clienteBusca.isPresent()){
         Cliente ClientePut =  clienteBusca.get();
         ClientePut.setNome(cliente.getNome());
         ClientePut.setNumeroCelular(cliente.getNumeroCelular());
         ClientePut.setSenha(cliente.getSenha());
         clienteRepository.save(ClientePut); 
           return Optional.ofNullable(ClientePut);
       } else {
         return Optional.empty();
       }
   }



}
