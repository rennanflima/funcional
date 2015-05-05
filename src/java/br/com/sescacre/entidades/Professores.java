/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.sescacre.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author Rennan Francisco
 */
@Entity
public class Professores extends Pessoa{
    
    @Column(unique = true)
    private Integer cref;
    @Column(length = 10)
    private String crefUf;
    @OneToOne
    @JoinColumn(nullable = false)
    private Usuarios usuario = new Usuarios();

    public Integer getCref() {
        return cref;
    }

    public void setCref(Integer cref) {
        this.cref = cref;
    }

    public String getCrefUf() {
        return crefUf;
    }

    public void setCrefUf(String crefUf) {
        this.crefUf = crefUf;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
}
