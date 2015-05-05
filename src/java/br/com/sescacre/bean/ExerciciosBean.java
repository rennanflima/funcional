/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sescacre.bean;

import br.com.sescacre.dao.ExerciciosDAO;
import br.com.sescacre.dao.ExerciciosDoTreinoDAO;
import br.com.sescacre.dao.SubPilar1DAO;
import br.com.sescacre.dao.SubPilar2DAO;
import br.com.sescacre.entidades.Exercicios;
import br.com.sescacre.entidades.ExerciciosDoTreino;
import br.com.sescacre.entidades.SubPilares1;
import br.com.sescacre.entidades.SubPilares2;
import java.io.Serializable;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Rennan Francisco
 */
@ManagedBean
@ViewScoped
public class ExerciciosBean implements Serializable {

    private Exercicios exercicio = new Exercicios();
    private List<Exercicios> exercicios = new ArrayList<Exercicios>();
    private List<ExerciciosDoTreino> exerciciosTreino = new ArrayList<ExerciciosDoTreino>();
    private Integer idPilares;
    private Integer idSubPilares1;
    private Integer idSubPilares2;
    private List<SubPilares1> subpilares1 = new ArrayList<SubPilares1>();
    private List<SubPilares2> subpilares2 = new ArrayList<SubPilares2>();

    @PostConstruct
    public void construct() {
        exercicios = new ExerciciosDAO().ListaTodos();
    }

    public Exercicios getExercicio() {
        return exercicio;
    }

    public void setExercicio(Exercicios exercicio) {
        this.exercicio = exercicio;
    }

    public List<Exercicios> getExercicios() {
        return exercicios;
    }

    public void setExercicios(List<Exercicios> exercicios) {
        this.exercicios = exercicios;
    }

    public Integer getIdSubPilares2() {
        if (exercicio.getIdExercicio() == null) {
            return idSubPilares2;
        } else {
            Integer temp = exercicio.getSubPilar2().getIdSubPilar2();
            if (idSubPilares2 == null) {
                return temp;
            } else {
                return idSubPilares2;
            }
        }
    }

    public void setIdSubPilares2(Integer idSubPilares2) {
        this.idSubPilares2 = idSubPilares2;
    }

    public Integer getIdSubPilares1() {
        if (exercicio.getIdExercicio() == null) {
            return idSubPilares1;
        } else {
            Integer temp = exercicio.getSubPilar2().getSubPilar1().getIdSubPilar1();
            if (idSubPilares1 == null) {
                return temp;
            } else {
                return idSubPilares1;
            }
        }
    }

    public void setIdSubPilares1(Integer idSubPilares1) {
        this.idSubPilares1 = idSubPilares1;
    }

    public Integer getIdPilares() {
        if (exercicio.getIdExercicio() == null) {
            return idPilares;
        } else {
            Integer temp = exercicio.getSubPilar2().getSubPilar1().getPilar().getIdPilar();
            if (idPilares == null) {
                return temp;
            } else {
                return idPilares;
            }
        }
    }

    public void setIdPilares(Integer idPilares) {
        this.idPilares = idPilares;
    }

    public List<SubPilares1> getSubpilares1() {
        return subpilares1 = new SubPilar1DAO().pesquisaSubPilar1PorPilar(idPilares);
    }

    public void setSubpilares1(List<SubPilares1> subpilares1) {
        this.subpilares1 = subpilares1;
    }

    public List<SubPilares2> getSubpilares2() {
        return subpilares2 = new SubPilar2DAO().pesquisaSubPilar2PorSubPilar1(idSubPilares1);
    }

    public void setSubpilares2(List<SubPilares2> subpilares2) {
        this.subpilares2 = subpilares2;
    }

    public String salvar() {
        ExerciciosDAO exercicioD = new ExerciciosDAO();
        FacesContext msg = FacesContext.getCurrentInstance();
        try {
            SubPilares2 sp2 = new SubPilar2DAO().pesquisaPorId(idSubPilares2);
            exercicio.setSubPilar2(sp2);
            if (exercicio.getIdExercicio() == null) {
                exercicioD.salvar(exercicio);
                msg.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "O exercício '" + exercicio.getNome() + "' foi inserido com sucesso.", null));
            } else {
                exercicioD.alterar(exercicio);
                msg.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "O exercício '" + exercicio.getNome() + "' foi alterado com sucesso.", null));
                RequestContext.getCurrentInstance().execute("inserir.hide()");
            }
            limpar();
        } catch (SQLIntegrityConstraintViolationException ex) {
            msg.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Já existe um Exercício cadastrado com esse nome!", null));
        } catch (Exception e) {
            msg.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Ocorreu um erro ao inserir o exercício '" + exercicio.getNome() + "'", null));
        }
        construct();
        return null;
    }

    public String excluir() {
        ExerciciosDAO exercicioD = new ExerciciosDAO();
        FacesContext msg = FacesContext.getCurrentInstance();
        try {
            exerciciosTreino = new ExerciciosDoTreinoDAO().pesquisaExercicioTreinoPorExercicio(exercicio.getIdExercicio());
            if (exerciciosTreino.isEmpty()) {
                exercicioD.excluir(exercicio);
                msg.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "O exercício '" + exercicio.getNome() + "' foi excluído com sucesso.", null));
                exercicio = new Exercicios();
            } else {
                msg.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "O exercício '" + exercicio.getNome() + "' possui dependências com a tabela exercícios do treino. É necessário corrigí-las.", null));
            }
        } catch (Exception e) {
            msg.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Ocorreu um erro ao excluir o exercício '" + exercicio.getNome() + "'", null));
        }
        construct();
        return null;
    }

    public String limpar() {
        exercicio = new Exercicios();
        idSubPilares2 = null;
        idSubPilares1 = null;
        idPilares = null;
        subpilares1 = new ArrayList<SubPilares1>();
        subpilares2 = new ArrayList<SubPilares2>();
        return null;
    }
}
