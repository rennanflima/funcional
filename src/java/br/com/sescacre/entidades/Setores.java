/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.sescacre.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Rennan Francisco
 */
@Entity
public class Setores {
    
    @Id
    @GeneratedValue
    private Integer idSetores;
    @Column(nullable = false, length = 60, unique = true)
    private String nome;

    public Integer getIdSetores() {
        return idSetores;
    }

    public void setIdSetores(Integer idSetores) {
        this.idSetores = idSetores;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }        
}
