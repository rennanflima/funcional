/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sescacre.bean;

import br.com.sescacre.dao.AlunosDAO;
import br.com.sescacre.dao.PessoasDAO;
import br.com.sescacre.entidades.Alunos;
import br.com.sescacre.entidades.Pessoas;
import java.io.Serializable;
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
public class AlunosBean implements Serializable {

    private Alunos aluno = new Alunos();
    private List<Alunos> alunos = new ArrayList<Alunos>();
    private Pessoas pessoas = new Pessoas();

    @PostConstruct
    public void construct() {
        alunos = new AlunosDAO().ListaTodos();
    }

    public Alunos getAluno() {
        return aluno;
    }

    public void setAluno(Alunos aluno) {
        this.aluno = aluno;
    }

    public List<Alunos> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Alunos> alunos) {
        this.alunos = alunos;
    }

    public Pessoas getPessoas() {
        return pessoas;
    }

    public void setPessoas(Pessoas pessoas) {
        this.pessoas = pessoas;
    }

    public List<Pessoas> completePessoas(String nome){
        return new PessoasDAO().pesquisaPessoaPorNome(nome);
    }
    
    public String salvar() {
        AlunosDAO alunoD = new AlunosDAO();
        FacesContext msg = FacesContext.getCurrentInstance();
        try {
            aluno.setPessoa(pessoas);
            if (aluno.getIdAluno() == null) {
                alunoD.salvar(aluno);
                msg.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "O aluno '" + aluno.getPessoa().getNome() + "' foi inserido com sucesso.", null));
            } else {
                alunoD.alterar(aluno);
                msg.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "O aluno '" + aluno.getPessoa().getNome() + "' foi alterado com sucesso.", null));
                RequestContext.getCurrentInstance().execute("inserir.hide()");
            }
            limpar();
        } catch (Exception e) {
            msg.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Ocorreu um erro ao inserir o aluno '" + aluno.getPessoa().getNome() + "'", null));
        }
        construct();
        return null;
    }

    public String excluir() {
        return null;
    }

    public String limpar() {
        aluno = new Alunos();
        pessoas = new Pessoas();
        return null;
    }
    
    public void carregaCliente(){
        new PessoasBean().setCliente(pessoas);
    }
}
