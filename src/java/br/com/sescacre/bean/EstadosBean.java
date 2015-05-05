/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sescacre.bean;

import br.com.sescacre.dao.CidadesDAO;
import br.com.sescacre.dao.EstadosDAO;
import br.com.sescacre.entidades.Cidades;
import br.com.sescacre.entidades.Estados;
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
public class EstadosBean implements Serializable {

    private Estados estado = new Estados();
    private List<Estados> estados = new ArrayList<Estados>();
    private List<Cidades> cidades = new ArrayList<Cidades>();

    @PostConstruct
    public void construct() {
        estados = new EstadosDAO().ListaTodos();
    }

    public Estados getEstado() {
        return estado;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }

    public List<Estados> getEstados() {
        return estados;
    }

    public void setEstados(List<Estados> estados) {
        this.estados = estados;
    }

    public String salvar() {
        EstadosDAO estadoD = new EstadosDAO();
        FacesContext msg = FacesContext.getCurrentInstance();
        try {
            if (estado.getIdEstado() == null) {
                estadoD.salvar(estado);
                msg.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "O estado '" + estado.getNome() + "' foi inserido com sucesso.", null));
            } else {
                estadoD.alterar(estado);
                msg.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "O estado '" + estado.getNome() + "' foi alterado com sucesso.", null));
                RequestContext.getCurrentInstance().execute("inserir.hide()");
            }
            limpar();
        } catch (SQLIntegrityConstraintViolationException ex) {
            msg.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Já existe um Estado cadastrado com esse nome!", null));
        } catch (Exception e) {
            msg.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Ocorreu um erro ao inserir o estado '" + estado.getNome() + "'", null));
        }
        construct();
        return null;
    }

    public String excluir() {
        EstadosDAO estadoD = new EstadosDAO();
        FacesContext msg = FacesContext.getCurrentInstance();
        try {
            cidades = new CidadesDAO().pesquisaCidadesPorEstados(estado.getIdEstado());
            if (cidades.isEmpty()) {
                estadoD.excluir(estado);
                msg.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "O estado '" + estado.getNome() + "' foi excluído com sucesso.", null));
                limpar();
            } else {
                msg.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "O estado '" + estado.getNome() + "' possui dependências com a tabela cidades. É necessário corrigí-las.", null));
            }
        } catch (Exception e) {
            msg.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Ocorreu um erro ao excluir o estado '" + estado.getNome() + "'", null));
        }
        construct();
        return null;
    }

    public String limpar() {
        estado = new Estados();
        return null;
    }
}
