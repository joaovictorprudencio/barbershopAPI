package com.example.barbershop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.barbershop.Exception.ClienteException;
import com.example.barbershop.entitys.Cliente;
import com.example.barbershop.repository.ClienteRepository;

@Service
public class ClienteService {
  @Autowired
  ClienteRepository clienteRepository;

  public Cliente CriarCliente(Cliente cliente) {
    Optional<Cliente> ClienteExistente = clienteRepository.findByNome(cliente.getNome());

    if (ClienteExistente.isPresent()) {
      throw new ClienteException("O nome de usuário já está em uso: " + cliente.getNome());
    }
    return clienteRepository.save(cliente);
  }

  public Optional<Cliente> BuscarCliente(Cliente cliente) {

         Optional<Cliente> clienteExistente = clienteRepository.findByNome(cliente.getNome());

        if(clienteExistente.isEmpty()){
         throw new ClienteException("O usuario : " + cliente.getNome() + " não existe");
        }
       return clienteExistente;
  }

  public void DeletarCliente(Cliente cliente) {
    Optional<Cliente> clienteOptinal = clienteRepository.findByNome(cliente.getNome());

    if(clienteOptinal.isEmpty()){
      throw new ClienteException("O usuario : " + cliente.getNome() + " não existe");
    }

    Cliente ClienteExistente = clienteOptinal.get();

    clienteRepository.deleteById(ClienteExistente.getId());
  }

  public Cliente AtualizarCliente(Cliente cliente) {
    Optional<Cliente> clienteBusca = clienteRepository.findByNome(cliente.getNome());

    if (clienteBusca.isEmpty()) {
      throw new ClienteException("O cliente : " + cliente.getNome() + " não existe");
    }

    Cliente newCliente = clienteBusca.get();
    newCliente.setNome(cliente.getNome());
    newCliente.setEmail(cliente.getEmail());
    newCliente.setSenha(cliente.getSenha());
    newCliente.setNumeroCelular(cliente.getNumeroCelular());
    return clienteRepository.save(newCliente);
  }

}
