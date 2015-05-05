/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sescacre.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Rennan Francisco
 */
@Entity
public class Alunos implements Serializable {

    @Id
    @GeneratedValue
    private Integer idAluno;
    @Column(length = 30)
    private String nivel;
    private Integer qtdAulas;
    private String obs;
    @Column(length = 60)
    private String respNome;
    @Column(length = 16)
    private String comercialResp;
    @Column(length = 16)
    private String residencialResp;
    @Column(length = 16)
    private String celularResp;
    @Column(length = 10)
    private String status;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Pessoas pessoa = new Pessoas();
    @ManyToOne
    @JoinColumn()
    private Funcionarios professor = new Funcionarios();

    public Integer getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Integer idAluno) {
        this.idAluno = idAluno;
    }

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

    public String getRespNome() {
        return respNome;
    }

    public void setRespNome(String respNome) {
        this.respNome = respNome;
    }

    public String getComercialResp() {
        return comercialResp;
    }

    public void setComercialResp(String comercialResp) {
        this.comercialResp = comercialResp;
    }

    public String getResidencialResp() {
        return residencialResp;
    }

    public void setResidencialResp(String residencialResp) {
        this.residencialResp = residencialResp;
    }

    public String getCelularResp() {
        return celularResp;
    }

    public void setCelularResp(String celularResp) {
        this.celularResp = celularResp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Pessoas getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoas pessoa) {
        this.pessoa = pessoa;
    }

    public Funcionarios getProfessor() {
        return professor;
    }

    public void setProfessor(Funcionarios professor) {
        this.professor = professor;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.idAluno);
        hash = 23 * hash + Objects.hashCode(this.nivel);
        hash = 23 * hash + Objects.hashCode(this.qtdAulas);
        hash = 23 * hash + Objects.hashCode(this.obs);
        hash = 23 * hash + Objects.hashCode(this.respNome);
        hash = 23 * hash + Objects.hashCode(this.comercialResp);
        hash = 23 * hash + Objects.hashCode(this.residencialResp);
        hash = 23 * hash + Objects.hashCode(this.celularResp);
        hash = 23 * hash + Objects.hashCode(this.status);
        hash = 23 * hash + Objects.hashCode(this.pessoa);
        hash = 23 * hash + Objects.hashCode(this.professor);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Alunos other = (Alunos) obj;
        if (!Objects.equals(this.idAluno, other.idAluno)) {
            return false;
        }
        if (!Objects.equals(this.nivel, other.nivel)) {
            return false;
        }
        if (!Objects.equals(this.qtdAulas, other.qtdAulas)) {
            return false;
        }
        if (!Objects.equals(this.obs, other.obs)) {
            return false;
        }
        if (!Objects.equals(this.respNome, other.respNome)) {
            return false;
        }
        if (!Objects.equals(this.comercialResp, other.comercialResp)) {
            return false;
        }
        if (!Objects.equals(this.residencialResp, other.residencialResp)) {
            return false;
        }
        if (!Objects.equals(this.celularResp, other.celularResp)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.pessoa, other.pessoa)) {
            return false;
        }
        if (!Objects.equals(this.professor, other.professor)) {
            return false;
        }
        return true;
    }
    
}
