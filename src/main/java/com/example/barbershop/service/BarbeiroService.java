package com.example.barbershop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.barbershop.Exception.BarbeiroException;
import com.example.barbershop.entitys.Barbeiro;
import com.example.barbershop.repository.BarbeiroRepository;

@Service
public class BarbeiroService {
    @Autowired
    BarbeiroRepository barbeiroRepository;

    public Barbeiro CriarBarbeiro(Barbeiro barbeiro) {
        Optional<Barbeiro> barbeiroOptinal = barbeiroRepository.findByNome(barbeiro.getNome());
        if (barbeiroOptinal.isPresent()) {
            throw new BarbeiroException("O nome de usuário já está em uso: " + barbeiro.getNome());
        }
        return barbeiroRepository.save(barbeiro);
    }

    public Optional<Barbeiro> BuscarBarbeiro(Barbeiro barbeiro) {
        Optional<Barbeiro> barbeiroOptinal = barbeiroRepository.findByNome(barbeiro.getNome());

        if (barbeiroOptinal.isEmpty()) {
            throw new BarbeiroException("O Usuario não está cadastrado: " + barbeiro.getNome());
        }
        return barbeiroOptinal;
    }

    public  Barbeiro  BuscarBarbeiroNome(String nome ){
        Optional<Barbeiro> barbeiroOptional = barbeiroRepository.findByNome(nome);
        Barbeiro barbeiro = barbeiroOptional.get();

        if(barbeiroOptional.isEmpty()){
            throw new BarbeiroException("O Usuario não está cadastrado: ");
        }

        return barbeiro;
    }


    public Barbeiro AtualizarBarbeiro(Barbeiro barbeiro) {
        Optional<Barbeiro> barbeiroOptinal = barbeiroRepository.findByNome(barbeiro.getNome());

        if (barbeiroOptinal.isEmpty()) {
            throw new BarbeiroException("O usuario: " + barbeiro.getNome() + " ainda não é cadastrado no sistema");
        }

        Barbeiro newBarbeiro = barbeiroOptinal.get();
        newBarbeiro.setNome(barbeiro.getNome());
        newBarbeiro.setNumeroCelular(barbeiro.getNumeroCelular());
        newBarbeiro.setSenha(barbeiro.getSenha());

        return barbeiroRepository.save(newBarbeiro);

    }

    public void DeletarBarbeiro(Barbeiro barbeiro) {
        Optional<Barbeiro> barbeiroOptinal = barbeiroRepository.findByNome(barbeiro.getNome());

        if (barbeiroOptinal.isEmpty()) {
            throw new BarbeiroException("O usuario: " + barbeiro.getNome() + " ainda não é cadastrado no sistema");
        }
        barbeiroRepository.deleteById(barbeiro.getId());

    }

}
