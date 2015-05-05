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
public class SubPilares1 implements Serializable {
    
    @Id
    @GeneratedValue
    private Integer idSubPilar1;
    @Column(nullable = false, length = 60, unique = true)
    private String nome;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Pilares pilar = new Pilares();

    public Integer getIdSubPilar1() {
        return idSubPilar1;
    }

    public void setIdSubPilar1(Integer idSubPilar1) {
        this.idSubPilar1 = idSubPilar1;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Pilares getPilar() {
        return pilar;
    }

    public void setPilar(Pilares pilar) {
        this.pilar = pilar;
    }
}
