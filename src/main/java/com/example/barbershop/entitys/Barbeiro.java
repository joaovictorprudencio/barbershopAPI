package com.example.barbershop.entitys;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Barbeiro {
    
    
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id ;
    private String nome ;
    private String senha;
    private String email;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    private String NumeroCelular ;
    
     @OneToMany(mappedBy = "barbeiro", cascade = CascadeType.ALL)
    private  List<Horarios> horarios ;



    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
   

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroCelular() {
        return this.NumeroCelular;
    }

    public void setNumeroCelular(String NumeroCelular) {
        this.NumeroCelular = NumeroCelular;
    }


    public List<Horarios> getHorarios() {
        return this.horarios;
    }

    public void setHorarios(List<Horarios> horarios) {
        this.horarios = horarios;
    }
  


}
