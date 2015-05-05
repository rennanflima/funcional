/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sescacre.dao;

import br.com.sescacre.entidades.Pilares;
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
public class PilaresDAO {

    public void salvar(Pilares p) throws Exception {
        Session s = HibernateUtil.getSession();
        try {
            Transaction t = s.beginTransaction();
            s.save(p);
            t.commit();
        } catch (Exception ex) {
            throw new SQLIntegrityConstraintViolationException();
        } finally {
            s.close();
        }
    }

    public void alterar(Pilares p) throws Exception {
        Session s = HibernateUtil.getSession();
        try {
            Transaction t = s.beginTransaction();
            s.update(p);
            t.commit();
        } catch (Exception e) {
            throw new SQLIntegrityConstraintViolationException();
        } finally {
            s.close();
        }
    }

    public void excluir(Pilares p) {
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        s.delete(p);
        t.commit();
        s.close();
    }

    public List<Pilares> ListaTodos() {
        Session s = HibernateUtil.getSession();
        Query q = s.createQuery("from Pilares");
        List<Pilares> lista = q.list();
        s.close();
        return lista;
    }

    public Pilares pesquisaPorId(Integer id) {
        Session s = HibernateUtil.getSession();
        Pilares p = (Pilares) s.load(Pilares.class, id);
        s.close();
        return p;
    }
}
