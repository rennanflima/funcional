/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sescacre.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Rennan Francisco
 */
@Entity
public class Treinos implements Serializable {

    @Id
    @GeneratedValue
    private Integer idTreino;
    @Column(length = 10)
    private String status;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataCad;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataVenc;
    private Integer diaSemana;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Funcionarios professor = new Funcionarios();
    @ManyToOne
    @JoinColumn(nullable = false)
    private Alunos aluno = new Alunos();

    public Integer getIdTreino() {
        return idTreino;
    }

    public void setIdTreino(Integer idTreino) {
        this.idTreino = idTreino;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDataCad() {
        return dataCad;
    }

    public void setDataCad(Date dataCad) {
        this.dataCad = dataCad;
    }

    public Date getDataVenc() {
        return dataVenc;
    }

    public void setDataVenc(Date dataVenc) {
        this.dataVenc = dataVenc;
    }

    public Integer getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(Integer diaSemana) {
        this.diaSemana = diaSemana;
    }

    public Funcionarios getProfessor() {
        return professor;
    }

    public void setProfessor(Funcionarios professor) {
        this.professor = professor;
    }

    public Alunos getAluno() {
        return aluno;
    }

    public void setAluno(Alunos aluno) {
        this.aluno = aluno;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.idTreino);
        hash = 97 * hash + Objects.hashCode(this.status);
        hash = 97 * hash + Objects.hashCode(this.dataCad);
        hash = 97 * hash + Objects.hashCode(this.dataVenc);
        hash = 97 * hash + Objects.hashCode(this.diaSemana);
        hash = 97 * hash + Objects.hashCode(this.professor);
        hash = 97 * hash + Objects.hashCode(this.aluno);
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
        final Treinos other = (Treinos) obj;
        if (!Objects.equals(this.idTreino, other.idTreino)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.dataCad, other.dataCad)) {
            return false;
        }
        if (!Objects.equals(this.dataVenc, other.dataVenc)) {
            return false;
        }
        if (!Objects.equals(this.diaSemana, other.diaSemana)) {
            return false;
        }
        if (!Objects.equals(this.professor, other.professor)) {
            return false;
        }
        if (!Objects.equals(this.aluno, other.aluno)) {
            return false;
        }
        return true;
    }
}
