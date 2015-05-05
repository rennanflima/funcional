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
public class Cidades implements Serializable {
    
    @Id
    @GeneratedValue
    private Integer idCidades;
    @Column(nullable = false,length = 60)
    private String nome;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Estados estado = new Estados();

    public Integer getIdCidades() {
        return idCidades;
    }

    public void setIdCidades(Integer idCidades) {
        this.idCidades = idCidades;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estados getEstado() {
        return estado;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }    
}
