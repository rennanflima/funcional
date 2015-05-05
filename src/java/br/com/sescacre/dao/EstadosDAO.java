/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sescacre.dao;

import br.com.sescacre.entidades.Estados;
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
public class EstadosDAO {

    public void salvar(Estados es) throws Exception {
        Session s = HibernateUtil.getSession();
        try {
            Transaction t = s.beginTransaction();
            s.save(es);
            t.commit();
        } catch (Exception ex) {
            throw new SQLIntegrityConstraintViolationException();
        } finally {
            s.close();
        }
    }

    public void alterar(Estados es) throws Exception {
        Session s = HibernateUtil.getSession();
        try {
            Transaction t = s.beginTransaction();
            s.update(es);
            t.commit();
        } catch (Exception e) {
            throw new SQLIntegrityConstraintViolationException();
        } finally {
            s.close();
        }
    }

    public void excluir(Estados es) {
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        s.delete(es);
        t.commit();
        s.close();
    }

    public List<Estados> ListaTodos() {
        Session s = HibernateUtil.getSession();
        Query q = s.createQuery("from Estados");
        List<Estados> lista = q.list();
        s.close();
        return lista;
    }

    public Estados pesquisaPorId(Integer id) {
        Session s = HibernateUtil.getSession();
        //Estados e = (Estados) s.load(Estados.class, id);
       return (Estados) s.load(Estados.class, id);
//        s.close();
  //      return e;
    }
    
    public Estados pesquisaEstadoPorUF(String uf){
        Session s = HibernateUtil.getSession();
        Query q = s.createQuery("from Estados e where e.sigla = :uf");
        q.setParameter("uf", uf);
        Estados estado = (Estados) q.uniqueResult();
        s.close();
        return estado;
    }
}
