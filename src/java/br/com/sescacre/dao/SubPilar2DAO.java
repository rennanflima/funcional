/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sescacre.dao;

import br.com.sescacre.entidades.SubPilares2;
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
public class SubPilar2DAO {

    public void salvar(SubPilares2 sp2) throws Exception {
        Session s = HibernateUtil.getSession();
        try {
            Transaction t = s.beginTransaction();
            s.save(sp2);
            t.commit();
        } catch (Exception ex) {
            throw new SQLIntegrityConstraintViolationException();
        } finally {
            s.close();
        }
    }

    public void alterar(SubPilares2 sp2) throws Exception {
        Session s = HibernateUtil.getSession();
        try {
            Transaction t = s.beginTransaction();
            s.update(sp2);
            t.commit();
        } catch (Exception e) {
            throw new SQLIntegrityConstraintViolationException();
        } finally {
            s.close();
        }
    }

    public void excluir(SubPilares2 sp2) {
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        s.delete(sp2);
        t.commit();
        s.close();
    }

    public List<SubPilares2> ListaTodos() {
        Session s = HibernateUtil.getSession();
        Query q = s.createQuery("from SubPilares2");
        List<SubPilares2> lista = q.list();
        s.close();
        return lista;
    }

    public SubPilares2 pesquisaPorId(Integer id) {
        Session s = HibernateUtil.getSession();
        SubPilares2 sp2 = (SubPilares2) s.load(SubPilares2.class, id);
        s.close();
        return sp2;
    }

    public List<SubPilares2> pesquisaSubPilar2PorSubPilar1(Integer id) {//join
        Session s = HibernateUtil.getSession();
        Query q = s.createQuery("from SubPilares2 sp2 where sp2.subPilar1.id = :subpilar1 order by sp2.nome");
        q.setParameter("subpilar1", id);
        List<SubPilares2> lista = q.list();
        s.close();
        return lista;
    }
}
