/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.sescacre.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Rennan Francisco
 */
@Entity
public class Exercicios implements Serializable {
    
    @Id
    @GeneratedValue
    private Integer idExercicio;
    @Column(nullable = false, length = 50, unique = true)
    private String nome;
    @ManyToOne
    @JoinColumn(nullable = false)
    private SubPilares2 subPilar2 = new SubPilares2();

    public Integer getIdExercicio() {
        return idExercicio;
    }

    public void setIdExercicio(Integer idExercicio) {
        this.idExercicio = idExercicio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public SubPilares2 getSubPilar2() {
        return subPilar2;
    }

    public void setSubPilar2(SubPilares2 subPilar2) {
        this.subPilar2 = subPilar2;
    }
}
