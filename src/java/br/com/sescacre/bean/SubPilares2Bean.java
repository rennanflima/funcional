/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sescacre.bean;

import br.com.sescacre.dao.ExerciciosDAO;
import br.com.sescacre.dao.SubPilar1DAO;
import br.com.sescacre.dao.SubPilar2DAO;
import br.com.sescacre.entidades.Exercicios;
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
public class SubPilares2Bean implements Serializable{

    private SubPilares2 subpilar2 = new SubPilares2();
    private List<SubPilares2> subpilares2 = new ArrayList<SubPilares2>();
    private List<Exercicios> exercicios = new ArrayList<Exercicios>();
    private Integer idPilares;
    private Integer idSubPilares1;
    private List<SubPilares1> subpilares1 = new ArrayList<SubPilares1>();
    

    @PostConstruct
    public void construct() {
        subpilares2 = new SubPilar2DAO().ListaTodos();
    }

    public SubPilares2 getSubpilar2() {
        return subpilar2;
    }

    public void setSubpilar2(SubPilares2 subpilar2) {
        this.subpilar2 = subpilar2;
    }

    public List<SubPilares2> getSubpilares2() {
        return subpilares2;
    }

    public void setSubpilares2(List<SubPilares2> subpilares2) {
        this.subpilares2 = subpilares2;
    }

    public Integer getIdPilares() {
        if (subpilar2.getIdSubPilar2() == null) {
            return idPilares;
        } else {
            Integer temp = subpilar2.getSubPilar1().getPilar().getIdPilar();
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

    public Integer getIdSubPilares1() {
        if (subpilar2.getIdSubPilar2() == null) {
            return idSubPilares1;
        } else {
            Integer temp = subpilar2.getSubPilar1().getIdSubPilar1();
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

    public List<SubPilares1> getSubpilares1() {
        return subpilares1 = new SubPilar1DAO().pesquisaSubPilar1PorPilar(idPilares);
    }

    public void setSubpilares1(List<SubPilares1> subpilares1) {
        this.subpilares1 = subpilares1;
    }

    public String salvar() {
        SubPilar2DAO subPilar2D = new SubPilar2DAO();
        FacesContext msg = FacesContext.getCurrentInstance();
        try {
            SubPilares1 sp1 = new SubPilar1DAO().pesquisaPorId(idPilares);
            subpilar2.setSubPilar1(sp1);
            if (subpilar2.getIdSubPilar2() == null) {
                subPilar2D.salvar(subpilar2);
                msg.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "O sub-pilar 2 '" + subpilar2.getNome() + "' foi inserido com sucesso.", null));
            } else {
                subPilar2D.alterar(subpilar2);
                msg.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "O sub-pilar 2 '" + subpilar2.getNome() + "' foi alterado com sucesso.", null));
                RequestContext.getCurrentInstance().execute("inserir.hide()");
            }
            limpar();
        } catch (SQLIntegrityConstraintViolationException ex) {
            msg.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Já existe um Sub-Pilar 2 cadastrado com esse nome!", null));
        } catch (Exception e) {
            msg.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Ocorreu um erro ao inserir o sub-pilar 2 '" + subpilar2.getNome() + "'", null));
        }
        construct();
        return null;
    }

    public String excluir() {
        SubPilar2DAO subPilar2D = new SubPilar2DAO();
        FacesContext msg = FacesContext.getCurrentInstance();
        try {
            exercicios = new ExerciciosDAO().pesquisaExercicioPorSubPilar2(subpilar2.getIdSubPilar2());
            if (exercicios.isEmpty()) {
                subPilar2D.excluir(subpilar2);
                msg.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "O sub-pilar 2 '" + subpilar2.getNome() + "' foi excluído com sucesso.", null));
                subpilar2 = new SubPilares2();
            } else {
                msg.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "O sub-pilar 2 '" + subpilar2.getNome() + "' possui dependências com a tabela exercícios. É necessário corrigí-las.", null));
            }
        } catch (Exception e) {
            msg.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Ocorreu um erro ao excluir o sub-pilar 2 '" + subpilar2.getNome() + "'", null));
        }
        construct();
        return null;
    }

    public String limpar() {
        subpilar2 = new SubPilares2();
        idPilares = null;
        idSubPilares1 = null;
        subpilares1 = new ArrayList<SubPilares1>();
        return null;
    }

}
