/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sescacre.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Rennan Francisco
 */
@Entity
public class ExerciciosDoTreino implements Serializable {

    @Id
    @GeneratedValue
    private Integer idExercicioDoTreino;
    private Integer serie;
    private Integer carga;
    private Integer rep;
    private String obs;
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date tempo;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Exercicios exercicio = new Exercicios();
    @ManyToOne
    @JoinColumn(nullable = false)
    private Treinos treino = new Treinos();

    public Integer getIdExercicioDoTreino() {
        return idExercicioDoTreino;
    }

    public void setIdExercicioDoTreino(Integer idExercicioDoTreino) {
        this.idExercicioDoTreino = idExercicioDoTreino;
    }

    public Integer getSerie() {
        return serie;
    }

    public void setSerie(Integer serie) {
        this.serie = serie;
    }

    public Integer getCarga() {
        return carga;
    }

    public void setCarga(Integer carga) {
        this.carga = carga;
    }

    public Integer getRep() {
        return rep;
    }

    public void setRep(Integer rep) {
        this.rep = rep;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Date getTempo() {
        return tempo;
    }

    public void setTempo(Date tempo) {
        this.tempo = tempo;
    }

    public Exercicios getExercicio() {
        return exercicio;
    }

    public void setExercicio(Exercicios exercicio) {
        this.exercicio = exercicio;
    }

    public Treinos getTreino() {
        return treino;
    }

    public void setTreino(Treinos treino) {
        this.treino = treino;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.idExercicioDoTreino);
        hash = 41 * hash + Objects.hashCode(this.serie);
        hash = 41 * hash + Objects.hashCode(this.carga);
        hash = 41 * hash + Objects.hashCode(this.rep);
        hash = 41 * hash + Objects.hashCode(this.obs);
        hash = 41 * hash + Objects.hashCode(this.tempo);
        hash = 41 * hash + Objects.hashCode(this.exercicio);
        hash = 41 * hash + Objects.hashCode(this.treino);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ExerciciosDoTreino other = (ExerciciosDoTreino) obj;
        if (!Objects.equals(this.idExercicioDoTreino, other.idExercicioDoTreino)) {
            return false;
        }
        if (!Objects.equals(this.serie, other.serie)) {
            return false;
        }
        if (!Objects.equals(this.carga, other.carga)) {
            return false;
        }
        if (!Objects.equals(this.rep, other.rep)) {
            return false;
        }
        if (!Objects.equals(this.obs, other.obs)) {
            return false;
        }
        if (!Objects.equals(this.tempo, other.tempo)) {
            return false;
        }
        if (!Objects.equals(this.exercicio, other.exercicio)) {
            return false;
        }
        if (!Objects.equals(this.treino, other.treino)) {
            return false;
        }
        return true;
    }

}
