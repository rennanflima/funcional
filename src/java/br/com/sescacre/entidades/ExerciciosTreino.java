/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.sescacre.entidades;

import java.util.Date;
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
public class ExerciciosTreino {
    
    @Id
    @GeneratedValue
    private Integer idExercicioTreino;
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

    public Integer getIdExercicioTreino() {
        return idExercicioTreino;
    }

    public void setIdExercicioTreino(Integer idExercicioTreino) {
        this.idExercicioTreino = idExercicioTreino;
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
}
