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
import javax.persistence.OneToOne;

/**
 *
 * @author Rennan Francisco
 */
@Entity
public class Funcionarios implements Serializable {

    @Id
    @GeneratedValue
    private Integer idFuncionario;
    @Column(unique = true)
    private Integer matricula;
    @Column(unique = true)
    private Integer cref;
    @Column()
    private String crefOrgExp;
    @Column(length = 20)
    private String tipo;
    @OneToOne
    @JoinColumn(nullable = false)
    private Usuarios usuario = new Usuarios();
    @ManyToOne
    @JoinColumn
    private CargosDoSetor cargo = new CargosDoSetor();
    @ManyToOne
    @JoinColumn(nullable = false)
    private Pessoas pessoa = new Pessoas();

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public Integer getCref() {
        return cref;
    }

    public void setCref(Integer cref) {
        this.cref = cref;
    }

    public String getCrefOrgExp() {
        return crefOrgExp;
    }

    public void setCrefOrgExp(String crefOrgExp) {
        this.crefOrgExp = crefOrgExp;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public CargosDoSetor getCargo() {
        return cargo;
    }

    public void setCargo(CargosDoSetor cargo) {
        this.cargo = cargo;
    }

    public Pessoas getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoas pessoa) {
        this.pessoa = pessoa;
    }

}
