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

/**
 *
 * @author Rennan Francisco
 */
@Entity
public class Pilares implements Serializable {
    
    @Id
    @GeneratedValue
    private Integer idPilares;
    @Column(nullable = false, length = 60, unique = true)
    private String nome;

    public Integer getIdPilares() {
        return idPilares;
    }

    public void setIdPilares(Integer idPilares) {
        this.idPilares = idPilares;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }   
}
