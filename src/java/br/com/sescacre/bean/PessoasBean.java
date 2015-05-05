/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sescacre.bean;

import br.com.sescacre.dao.CidadesDAO;
import br.com.sescacre.dao.ContatosDAO;
import br.com.sescacre.dao.EnderecosDAO;
import br.com.sescacre.dao.EstadosDAO;
import br.com.sescacre.dao.PessoasDAO;
import br.com.sescacre.entidades.Cidades;
import br.com.sescacre.entidades.Contatos;
import br.com.sescacre.entidades.Estados;
import br.com.sescacre.entidades.Pessoas;
import br.com.sescacre.util.WebServiceCep;
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
public class PessoasBean implements Serializable {

    private Pessoas cliente = new Pessoas();
    private List<Pessoas> clientes = new ArrayList<Pessoas>();
    private List<Cidades> cidades = new ArrayList<Cidades>();
    private List<Contatos> contatos = new ArrayList<Contatos>();
    private Integer idEstado;
    private Integer idCidade;
    private String celular;
    private String fone;
    private String com;
    private String email;

    @PostConstruct
    public void construct() {
        clientes = new PessoasDAO().ListaTodos();
    }

    public Pessoas getCliente() {
        return cliente;
    }

    public void setCliente(Pessoas cliente) {
        this.cliente = cliente;
    }

    public Integer getIdEstado() {
        if (cliente.getIdPessoa() == null) {
            return idEstado;
        } else {
            Integer temp = cliente.getEndereco().getCidade().getEstado().getIdEstado();
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

    public Integer getIdCidade() {
        if (cliente.getIdPessoa() == null) {
            return idCidade;
        } else {
            Integer temp = cliente.getEndereco().getCidade().getIdCidade();
            if (idCidade == null) {
                return temp;
            } else {
                return idCidade;
            }
        }
    }

    public void setIdCidade(Integer idCidade) {
        this.idCidade = idCidade;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Cidades> getCidades() {
        return cidades = new CidadesDAO().pesquisaCidadesPorEstados(idEstado);
    }

    public void setCidades(List<Cidades> cidades) {
        this.cidades = cidades;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public List<Pessoas> getClientes() {
        return clientes;
    }

    public void setClientes(List<Pessoas> clientes) {
        this.clientes = clientes;
    }

    public String salvar() {
        PessoasDAO pessoaD = new PessoasDAO();
        EnderecosDAO eD = new EnderecosDAO();
        ContatosDAO cd = new ContatosDAO();
        FacesContext msg = FacesContext.getCurrentInstance();
        try {
            Cidades cid = new CidadesDAO().pesquisaPorId(idCidade);
            cliente.getEndereco().setCidade(cid);
            addContato();
            cliente.setContatos(contatos);
            if (cliente.getIdPessoa() == null) {
                pessoaD.salvar(cliente);
                for (Contatos ct : contatos) {
                    cd.salvar(ct);
                }
                msg.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "O cliente '" + cliente.getNome() + "' foi inserida com sucesso.", null));
            } else {
                pessoaD.alterar(cliente);
                for (Contatos ct : contatos) {
                    cd.alterar(ct);
                }
                msg.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "O cliente '" + cliente.getNome() + "' foi alterado com sucesso.", null));
            }
            RequestContext.getCurrentInstance().execute("inserirCliente.hide()");
            limpar();
        } catch (SQLIntegrityConstraintViolationException ex) {
            msg.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Já existe um Cliente cadastrado com esse carteira!", null));
        } catch (Exception e) {
            msg.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Ocorreu um erro ao inserir um cliente '" + cliente.getNome() + "'", null));
        }
        return null;
    }

    public String excluir() {
        PessoasDAO pessoaD = new PessoasDAO();
        FacesContext msg = FacesContext.getCurrentInstance();
        try {
            //subpilar1 = new SubPilar1DAO().pesquisaSubPilar1PorPilar(cliente.getIdPessoa());
            //if (subpilar1.isEmpty()) {
            pessoaD.excluir(cliente);
            msg.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "O cliente '" + cliente.getNome() + "' foi excluído com sucesso.", null));
            cliente = new Pessoas();
            /*} else {
             msg.addMessage(null,
             new FacesMessage(FacesMessage.SEVERITY_ERROR,
             "O cliente '" + cliente.getNome() + "' possui dependências com a tabela sub-pilar 1. É necessário corrigí-las.", null));
             }*/
        } catch (Exception e) {
            msg.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Ocorreu um erro ao excluir o cliente '" + cliente.getNome() + "'", null));
        }
        return null;
    }

    public String limpar() {
        cliente = new Pessoas();
        idEstado = null;
        idCidade = null;
        fone = null;
        com = null;
        celular = null;
        email = null;
        contatos = new ArrayList<Contatos>();
        cidades = new ArrayList<Cidades>();
        return null;
    }

    public void addContato() {
        Contatos c = new Contatos();
        if (!fone.equals("") || fone != null) {
            c.setNome(fone);
            c.setTipo(1);
            contatos.add(c);
            c = new Contatos();
        } else if (!celular.equals("") || celular != null) {
            c.setNome(celular);
            c.setTipo(2);
            contatos.add(c);
            c = new Contatos();
        } else if (!email.equals("") || email != null) {
            c.setNome(email);
            c.setTipo(3);
            contatos.add(c);
            c = new Contatos();
        } else if (!com.equals("") || com != null) {
            c.setNome(com);
            c.setTipo(4);
            contatos.add(c);
            c = new Contatos();
        }
    }

    public void consultaCep() {
        //Faz a busca para o cep 58043-280
        WebServiceCep webServiceCep = WebServiceCep.searchCep(cliente.getEndereco().getCep());
        //A ferramenta de busca ignora qualquer caracter que não seja número.

        //caso a busca ocorra bem, imprime os resultados.
        if (webServiceCep.wasSuccessful()) {
            cliente.getEndereco().setCep(webServiceCep.getCep());
            cliente.getEndereco().setLogadouro(webServiceCep.getLogradouroFull());
            cliente.getEndereco().setBairro(webServiceCep.getBairro());
            Estados e = new EstadosDAO().pesquisaEstadoPorUF(webServiceCep.getUf());
            Cidades c = new CidadesDAO().pesquisaCidadesPorNome(webServiceCep.getCidade(), e.getIdEstado());
            setIdCidade(c.getIdCidade());
            setIdEstado(e.getIdEstado());
            /*System.out.println("Cep: " + webServiceCep.getCep());
             System.out.println("Logradouro: " + webServiceCep.getLogradouroFull());
             System.out.println("Bairro: " + webServiceCep.getBairro());
             System.out.println("Cidade: "
             + webServiceCep.getCidade() + "/" + webServiceCep.getUf());*/

            //caso haja problemas imprime as exceções.
        } else {
            System.out.println("Erro número: " + webServiceCep.getResulCode());
            System.out.println("Descrição do erro: " + webServiceCep.getResultText());
        }
    }
    /*public void validaCPF(FacesContext context, UIComponent component, Object value){
     String cpf = (String) value;
     boolean validado = true;
     int d1, d2;
     int digito1, digito2, resto;
     int digitoCPF;
     String nDigResult;
     String[] strIndesejados = {".", "-", " "};
     for (int i = 0; i < strIndesejados.length; i++) {
     cpf = cpf.replace(strIndesejados[i], "");
     }
     d1 = d2 = 0;
     digito1 = digito2 = resto = 0;
     for (int nCount = 0; nCount < cpf.length() - 1; nCount++) {
     digitoCPF = Integer.valueOf(cpf.substring(nCount - 1, nCount)).intValue();
     d1 = d1 + (11 - nCount) * digitoCPF;
     d2 = d2 + (12 - nCount) * digitoCPF;
     }
     resto = (d1 % 11);
     if (resto < 2) {
     digito1 = 0;
     } else {
     digito1 = 11 - resto;
     }
     d2 += 2 * digito1;
     resto = (d2 % 11);
     if (resto < 2) {
     digito2 = 0;
     } else {
     digito2 = 11 - resto;
     }
     String nDigVerific = cpf.substring(cpf.length() - 2, cpf.length());
     nDigResult = String.valueOf(digito1) + String.valueOf(digito2);
     if (!nDigVerific.equals(nDigResult)) {
     ((UIInput)component).setValid(false);
     FacesMessage msg = new FacesMessage("CPF invalido!");
     context.addMessage(component.getClientId(context), msg);
     }
     }*/

}
