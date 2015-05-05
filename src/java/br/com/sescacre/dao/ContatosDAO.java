/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sescacre.dao;

import br.com.sescacre.entidades.Contatos;
import br.com.sescacre.entidades.Pessoas;
import br.com.sescacre.entidades.SubPilares1;
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
public class ContatosDAO {

    public void salvar(Contatos c) throws Exception {
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        try {
            s.save(c);
            t.commit();
        } catch (Exception ex) {
            t.rollback();
            throw new SQLIntegrityConstraintViolationException();
        } finally {
            s.close();
        }
    }

    public void alterar(Contatos c) throws Exception {
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        try {
            s.merge(c);
            t.commit();
        } catch (Exception e) {
            t.rollback();
            throw new SQLIntegrityConstraintViolationException();
        } finally {
            s.close();
        }
    }

    public void excluir(Contatos c) {
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        s.delete(c);
        t.commit();
        s.close();
    }

    public List<Contatos> ListaTodos() {
        Session s = HibernateUtil.getSession();
        Query q = s.createQuery("from Contatos");
        List<Contatos> lista = q.list();
        s.close();
        return lista;
    }

    public Contatos pesquisaPorId(Integer id) {
        Session s = HibernateUtil.getSession();
        Contatos c = (Contatos) s.load(Contatos.class, id);
        s.close();
        return c;
    }

    public List<Contatos> pesquisaContatoPorPessoa(Pessoas p) {//join
        Session s = HibernateUtil.getSession();
        Query q = s.createQuery("from Contatos c where c.pessoa = :pessoa order by c.nome");
        q.setParameter("pessoa", p);
        List<Contatos> lista = q.list();
        s.close();
        return lista;
    }
}
