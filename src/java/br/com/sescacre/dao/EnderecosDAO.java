/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sescacre.dao;

import br.com.sescacre.entidades.Alunos;
import br.com.sescacre.entidades.Enderecos;
import br.com.sescacre.util.HibernateUtil;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Rennan Francisco
 */
public class EnderecosDAO {

    public void salvar(Enderecos end) throws Exception {
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        try {
            s.save(end);
            t.commit();
        } catch (Exception ex) {
            t.rollback();
            throw new SQLIntegrityConstraintViolationException();
        } finally {
            s.close();
        }
    }

    public void alterar(Enderecos end) throws Exception {
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        try {
            s.merge(end);
            t.commit();
        } catch (Exception e) {
            t.rollback();
            throw new SQLIntegrityConstraintViolationException();
        } finally {
            s.close();
        }
    }

    public void excluir(Enderecos end) {
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        s.delete(end);
        t.commit();
        s.close();
    }

    public List<Enderecos> ListaTodos() {
        Session s = HibernateUtil.getSession();
        Query q = s.createQuery("from Enderecos");
        List<Enderecos> lista = q.list();
        s.close();
        return lista;
    }

    public Enderecos pesquisaPorId(Integer id) {
        Session s = HibernateUtil.getSession();
        Enderecos end = (Enderecos) s.load(Enderecos.class, id);
        s.close();
        return end;
    }

    public List<Enderecos> pesquisaEnderecoPorCidade(Integer id) {//join
        Session s = HibernateUtil.getSession();
        Query q = s.createQuery("from Enderecos end where end.cidade.id = :cidades order by end.id");
        q.setParameter("cidades", id);
        List<Enderecos> lista = q.list();
        s.close();
        return lista;
    }
    
    public Enderecos pesquisaEnderecoPorPessoa(Integer id) {//join
        Session s = HibernateUtil.getSession();
        Query q = s.createQuery("from Enderecos end where end.pessoa.id = :pessoas order by end.id");
        q.setParameter("pessoas", id);
        Enderecos lista = (Enderecos) q.uniqueResult();
        s.close();
        return lista;
    }
}
