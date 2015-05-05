/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.sescacre.dao;

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
public class SubPilar1DAO {
    
    public void salvar(SubPilares1 sp1) throws Exception {
        Session s = HibernateUtil.getSession();
        try {
            Transaction t = s.beginTransaction();
            s.save(sp1);
            t.commit();
        } catch (Exception ex) {
            throw new SQLIntegrityConstraintViolationException();
        } finally {
            s.close();
        }
    }

    public void alterar(SubPilares1 sp1) throws Exception {
        Session s = HibernateUtil.getSession();
        try {
            Transaction t = s.beginTransaction();
            s.update(sp1);
            t.commit();
        } catch (Exception e) {
            throw new SQLIntegrityConstraintViolationException();
        } finally {
            s.close();
        }
    }

    public void excluir(SubPilares1 sp1) {
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        s.delete(sp1);
        t.commit();
        s.close();
    }

    public List<SubPilares1> ListaTodos() {
        Session s = HibernateUtil.getSession();
        Query q = s.createQuery("from SubPilares1");
        List<SubPilares1> lista = q.list();
        s.close();
        return lista;
    }
    
    public SubPilares1 pesquisaPorId (Integer id) {
        Session s = HibernateUtil.getSession();
        return (SubPilares1) s.load(SubPilares1.class, id);
    }
    
    public List<SubPilares1> pesquisaSubPilar1PorPilar(Integer id) {//join
        Session s = HibernateUtil.getSession();
        Query q = s.createQuery("from SubPilares1 sp1 where sp1.pilar.id = :pilares order by sp1.nome");
        q.setParameter("pilares", id);
        return q.list();
    }
}
