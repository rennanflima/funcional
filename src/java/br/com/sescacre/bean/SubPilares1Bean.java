/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.sescacre.bean;

import br.com.sescacre.dao.PilaresDAO;
import br.com.sescacre.dao.SubPilar1DAO;
import br.com.sescacre.dao.SubPilar2DAO;
import br.com.sescacre.entidades.Pilares;
import br.com.sescacre.entidades.SubPilares1;
import br.com.sescacre.entidades.SubPilares2;
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
public class SubPilares1Bean {
    
    private SubPilares1 subpilar1 = new SubPilares1();
    private List<SubPilares1> subpilares1 = new ArrayList<SubPilares1>();
    private List<SubPilares2> subpilar2 = new ArrayList<SubPilares2>();
    private Integer idPilares;
    
    @PostConstruct
    public void construct() {
        subpilares1 = new SubPilar1DAO().ListaTodos();
    }

    public SubPilares1 getSubpilar1() {
        return subpilar1;
    }

    public void setSubpilar1(SubPilares1 subpilar1) {
        this.subpilar1 = subpilar1;
    }

    public List<SubPilares1> getSubpilares1() {
        return subpilares1;
    }

    public void setSubpilares1(List<SubPilares1> subpilares1) {
        this.subpilares1 = subpilares1;
    }

    public Integer getIdPilares() {
        return idPilares;
    }

    public void setIdPilares(Integer idPilares) {
        this.idPilares = idPilares;
    }

    public String salvar() {
        SubPilar1DAO subPilar1D = new SubPilar1DAO();
        FacesContext msg = FacesContext.getCurrentInstance();
        try {
            Pilares p = new PilaresDAO().pesquisaPorId(idPilares);
            subpilar1.setPilar(p);
            if (subpilar1.getIdSubPilar1()== null) {
                subPilar1D.salvar(subpilar1);
                msg.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "O sub-pilar 1 '" + subpilar1.getNome() + "' foi inserido com sucesso.", null));
            } else {
                subPilar1D.alterar(subpilar1);
                msg.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "O sub-pilar 1 '" + subpilar1.getNome() + "' foi alterado com sucesso.", null));
                RequestContext.getCurrentInstance().execute("inserir.hide()");
            }
            limpar();
        } catch (SQLIntegrityConstraintViolationException ex) {
            msg.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Já existe um Sub-Pilar 1 cadastrado com esse nome!", null));
        } catch (Exception e) {
            msg.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Ocorreu um erro ao inserir o sub-pilar 1 '" + subpilar1.getNome()+"'", null));
        }
        construct();
        return null;
    }
    
    public String excluir() {
        SubPilar1DAO subPilar1D = new SubPilar1DAO();
        FacesContext msg = FacesContext.getCurrentInstance();
        try {
            subpilar2 = new SubPilar2DAO().pesquisaSubPilar2PorSubPilar1(subpilar1.getIdSubPilar1());
            if (subpilar2.isEmpty()) {
                subPilar1D.excluir(subpilar1);
                msg.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "O sub-pilar 1 '" + subpilar1.getNome() + "' foi excluído com sucesso.", null));
                subpilar1 = new SubPilares1();
            } else{
                msg.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "O sub-pilar 1 '" + subpilar1.getNome() + "' possui dependências com a tabela sub-pilar 2. É necessário corrigí-las.", null));
            }
        } catch (Exception e) {
            msg.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Ocorreu um erro ao excluir o sub-pilar 1 '" + subpilar1.getNome()+"'", null));
        }
        construct();
        return null;
    }

    public String limpar() {
        subpilar1 = new SubPilares1();
        idPilares = null;
        return null;
    }    
}
