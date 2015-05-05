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
public class Alunos extends Pessoa implements Serializable {
    
    @Column(length = 30)
    private String nivel;
    private Integer qtdAulas;
    private String obs;
    private Integer respMat;
    @Column(length = 60)
    private String respNome;

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public Integer getQtdAulas() {
        return qtdAulas;
    }

    public void setQtdAulas(Integer qtdAulas) {
        this.qtdAulas = qtdAulas;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Integer getRespMat() {
        return respMat;
    }

    public void setRespMat(Integer respMat) {
        this.respMat = respMat;
    }

    public String getRespNome() {
        return respNome;
    }

    public void setRespNome(String respNome) {
        this.respNome = respNome;
    }
    
}
