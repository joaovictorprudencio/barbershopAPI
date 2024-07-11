package com.example.barbershop.entitys;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Barbeiro {
    
    
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id ;
    private String nome ;
    private String NumeroCelular ;
    private  Horarios horarios ;



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

    public Horarios getHorarios() {
        return this.horarios;
    }

    public void setHorario(Horarios horarios) {
        this.horarios = horarios;
    }


}
