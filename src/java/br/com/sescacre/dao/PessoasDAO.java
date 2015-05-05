/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sescacre.dao;

import br.com.sescacre.entidades.Pessoas;
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
public class PessoasDAO {

    public Pessoas salvar(Pessoas p) throws Exception {
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        try {
            s.save(p);
            t.commit();
            return p;
        } catch (Exception ex) {
            t.rollback();
            throw new SQLIntegrityConstraintViolationException();
        } finally {
            s.close();
        }
    }

    public void alterar(Pessoas p) throws Exception {
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        try {
            s.merge(p);
            t.commit();
        } catch (Exception e) {
            t.rollback();
            throw new SQLIntegrityConstraintViolationException();
        } finally {
            s.close();
        }
    }

    public void excluir(Pessoas p) {
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        s.delete(p);
        t.commit();
        s.close();
    }

    public List<Pessoas> ListaTodos() {
        Session s = HibernateUtil.getSession();
        Query q = s.createQuery("from Pessoas");
        List<Pessoas> lista = q.list();
        s.close();
        return lista;
    }

    public Pessoas pesquisaPorId(Integer id) {
        Session s = HibernateUtil.getSession();
        return (Pessoas) s.load(Pessoas.class, id);
        //s.close();
        //return p;
    }
    
    public Pessoas pesquisaPessoaPorCPF(String cpf){
        Session s = HibernateUtil.getSession();
        Query q = s.createQuery("from Pessoas p where p.cpf = :cpf order by p.nome");
        q.setParameter("cpf", cpf);
        Pessoas a = (Pessoas) q.uniqueResult();
        s.close();
        return a;
    }
    
    public List<Pessoas> pesquisaPessoaPorNome(String nome){
        Session s = HibernateUtil.getSession();
        Query q = s.createQuery("from Pessoas p where p.nome like :nome");
        q.setParameter("nome", nome+"%");
        q.setMaxResults(5);
        List<Pessoas> lista = q.list();
        s.close();
        return lista;
    }
}
