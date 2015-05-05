/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.sescacre.dao;

import br.com.sescacre.entidades.Exercicios;
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
public class ExerciciosDAO {
    
    public void salvar(Exercicios exc) throws Exception {
        Session s = HibernateUtil.getSession();
        try {
            Transaction t = s.beginTransaction();
            s.save(exc);
            t.commit();
        } catch (Exception ex) {
            throw new SQLIntegrityConstraintViolationException();
        } finally {
            s.close();
        }
    }

    public void alterar(Exercicios exc) throws Exception {
        Session s = HibernateUtil.getSession();
        try {
            Transaction t = s.beginTransaction();
            s.update(exc);
            t.commit();
        } catch (Exception e) {
            throw new SQLIntegrityConstraintViolationException();
        } finally {
            s.close();
        }
    }

    public void excluir(Exercicios exc) {
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        s.delete(exc);
        t.commit();
        s.close();
    }

    public List<Exercicios> ListaTodos() {
        Session s = HibernateUtil.getSession();
        Query q = s.createQuery("from Exercicios");
        List<Exercicios> lista = q.list();
        s.close();
        return lista;
    }
    
    public Exercicios pesquisaPorId (Integer id) {
        Session s = HibernateUtil.getSession();
        return (Exercicios) s.load(Exercicios.class, id);
    }
    
    public List<Exercicios> pesquisaExercicioPorSubPilar2(Integer id) {//join
        Session s = HibernateUtil.getSession();
        Query q = s.createQuery("from Exercicios ex where ex.subPilar2.id = :subpilar2 order by ex.nome");
        q.setParameter("subpilar2", id);
        return q.list();
    }
}
