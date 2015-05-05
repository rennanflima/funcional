/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sescacre.bean;

import br.com.sescacre.dao.CidadesDAO;
import br.com.sescacre.dao.EnderecosDAO;
import br.com.sescacre.dao.EstadosDAO;
import br.com.sescacre.entidades.Cidades;
import br.com.sescacre.entidades.Enderecos;
import br.com.sescacre.entidades.Estados;
import java.io.Serializable;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
public class CidadesBean implements Serializable {

    private Cidades cidade = new Cidades();
    private List<Cidades> cidades = new ArrayList<Cidades>();
    private List<Enderecos> enderecos = new ArrayList<Enderecos>();
    private Integer idEstado;

    @PostConstruct
    public void construct() {
        cidades = new CidadesDAO().ListaTodos();
    }

    public Cidades getCidade() {
        return cidade;
    }

    public void setCidade(Cidades cidade) {
        this.cidade = cidade;
    }

    public List<Cidades> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidades> cidades) {
        this.cidades = cidades;
    }

    public Integer getIdEstado() {
        if (cidade.getIdCidade() == null) {
            return idEstado;
        } else {
            Integer temp = cidade.getEstado().getIdEstado();
            if (idEstado == null) {
                return temp;
            } else {
                return idEstado;
            }
        }
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String salvar() {
        CidadesDAO cidadeD = new CidadesDAO();
        FacesContext msg = FacesContext.getCurrentInstance();
        try {
            Estados es = new EstadosDAO().pesquisaPorId(idEstado);
            cidade.setEstado(es);
            Integer i = cidadeD.contaCapital(idEstado);
            if (i >= 1) {
                throw new IllegalArgumentException();
            } else {
                if (cidade.getIdCidade() == null) {
                    cidadeD.salvar(cidade);
                    msg.addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO,
                                    "A cidade '" + cidade.getNome() + "' foi inserida com sucesso.", null));
                } else {
                    cidadeD.alterar(cidade);
                    msg.addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO,
                                    "A cidade '" + cidade.getNome() + "' foi alterada com sucesso.", null));
                    RequestContext.getCurrentInstance().execute("inserir.hide()");
                }
            }
            limpar();
        } catch (IllegalArgumentException e) {
            msg.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Já existe uma capital ao estado!", null));
        } catch (SQLIntegrityConstraintViolationException ex) {
            msg.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Já existe uma Cidade cadastrado com esse nome no estado!", null));
        } catch (Exception e) {
            System.out.println(e);
            msg.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Ocorreu um erro ao inserir a cidade '" + cidade.getNome() + "'", null));
        }
        construct();
        return null;
    }

    public String excluir() {
        CidadesDAO cidadeD = new CidadesDAO();
        FacesContext msg = FacesContext.getCurrentInstance();
        try {
            enderecos = new EnderecosDAO().pesquisaEnderecoPorCidade(cidade.getIdCidade());
            if (enderecos.isEmpty()) {
                cidadeD.excluir(cidade);
                msg.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "A cidade '" + cidade.getNome() + "' foi excluída com sucesso.", null));
                cidade = new Cidades();
            } else {
                msg.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "A cidade '" + cidade.getNome() + "' possui dependências com a tabela endereços. É necessário corrigí-las.", null));
            }
        } catch (Exception e) {
            msg.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Ocorreu um erro ao excluir a cidade '" + cidade.getNome() + "'", null));
        }
        construct();
        return null;
    }

    public String limpar() {
        cidade = new Cidades();
        idEstado = null;
        return null;
    }
}
