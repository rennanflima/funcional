/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sescacre.bean;

import br.com.sescacre.dao.PilaresDAO;
import br.com.sescacre.dao.SubPilar1DAO;
import br.com.sescacre.entidades.Pilares;
import br.com.sescacre.entidades.SubPilares1;
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
public class PilaresBean implements Serializable {

    private Pilares pilar = new Pilares();
    private List<Pilares> pilares = new ArrayList<Pilares>();
    private List<SubPilares1> subpilar1 = new ArrayList<SubPilares1>();

    @PostConstruct
    public void construct() {
        pilares = new PilaresDAO().ListaTodos();
    }

    public Pilares getPilar() {
        return pilar;
    }

    public void setPilar(Pilares pilar) {
        this.pilar = pilar;
    }

    public List<Pilares> getPilares() {
        return pilares;
    }

    public void setPilares(List<Pilares> pilares) {
        this.pilares = pilares;
    }

    public String salvar() {
        PilaresDAO pilarD = new PilaresDAO();
        FacesContext msg = FacesContext.getCurrentInstance();
        try {
            if (pilar.getIdPilar() == null) {
                pilarD.salvar(pilar);
                msg.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "O pilar '" + pilar.getNome() + "' foi inserido com sucesso.", null));
            } else {
                pilarD.alterar(pilar);
                msg.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "O pilar '" + pilar.getNome() + "' foi alterado com sucesso.", null));
                RequestContext.getCurrentInstance().execute("inserir.hide()");
            }
            limpar();
        } catch (SQLIntegrityConstraintViolationException ex) {
            msg.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Já existe um Pilar cadastrado com esse nome!", null));
        } catch (Exception e) {
            msg.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Ocorreu um erro ao inserir o pilar '" + pilar.getNome() + "'", null));
        }
        construct();
        return null;
    }

    public String excluir() {
        PilaresDAO pilarD = new PilaresDAO();
        FacesContext msg = FacesContext.getCurrentInstance();
        try {
            subpilar1 = new SubPilar1DAO().pesquisaSubPilar1PorPilar(pilar.getIdPilar());
            if (subpilar1.isEmpty()) {
                pilarD.excluir(pilar);
                msg.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "O pilar '" + pilar.getNome() + "' foi excluído com sucesso.", null));
                pilar = new Pilares();
            } else {
                msg.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "O pilar '" + pilar.getNome() + "' possui dependências com a tabela sub-pilar 1. É necessário corrigí-las.", null));
            }
        } catch (Exception e) {
            msg.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Ocorreu um erro ao excluir o pilar '" + pilar.getNome() + "'", null));
        }
        construct();
        return null;
    }

    public String limpar() {
        pilar = new Pilares();
        return null;
    }
}
