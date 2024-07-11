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
        return clienteRepository.save(cliente);
    }

    
    public Optional<Cliente> BuscarCliente(Cliente cliente) {
         return clienteRepository.findByNome(cliente.getNome());
    }

   public void DeletarCliente(Cliente cliente ){
        clienteRepository.deleteById(cliente.getId());
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
