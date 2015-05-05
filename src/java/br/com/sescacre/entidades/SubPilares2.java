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
public class SubPilares2 implements Serializable {
    
    @Id
    @GeneratedValue
    private Integer idSubPilar2;
    @Column(nullable = false, length = 50, unique = true)
    private String nome;
    @ManyToOne
    @JoinColumn(nullable = false)
    private SubPilares1 subPilar1 = new SubPilares1();

    public Integer getIdSubPilar2() {
        return idSubPilar2;
    }

    public void setIdSubPilar2(Integer idSubPilar2) {
        this.idSubPilar2 = idSubPilar2;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public SubPilares1 getSubPilar1() {
        return subPilar1;
    }

    public void setSubPilar1(SubPilares1 subPilar1) {
        this.subPilar1 = subPilar1;
    }
}
