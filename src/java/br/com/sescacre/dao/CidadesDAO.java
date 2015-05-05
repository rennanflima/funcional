/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sescacre.dao;

import br.com.sescacre.entidades.Cidades;
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
public class CidadesDAO {

    public void salvar(Cidades cid) throws Exception {
        Session s = HibernateUtil.getSession();
        try {
            Transaction t = s.beginTransaction();
            s.save(cid);
            t.commit();
        } catch (Exception ex) {
            throw new SQLIntegrityConstraintViolationException();
        } finally {
            s.close();
        }
    }

    public void alterar(Cidades cid) throws Exception {
        Session s = HibernateUtil.getSession();
        try {
            Transaction t = s.beginTransaction();
            s.update(cid);
            t.commit();
        } catch (Exception e) {
            throw new SQLIntegrityConstraintViolationException();
        } finally {
            s.close();
        }
    }

    public void excluir(Cidades cid) {
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        s.delete(cid);
        t.commit();
        s.close();
    }

    public List<Cidades> ListaTodos() {
        Session s = HibernateUtil.getSession();
        Query q = s.createQuery("from Cidades");
        List<Cidades> lista = q.list();
        s.close();
        return lista;
    }

    public Cidades pesquisaPorId(Integer id) {
        Session s = HibernateUtil.getSession();
        Cidades c = (Cidades) s.load(Cidades.class, id);
        s.close();
        return c;
    }

    public List<Cidades> pesquisaCidadesPorEstados(Integer id) {//join
        Session s = HibernateUtil.getSession();
        Query q = s.createQuery("from Cidades cid where cid.estado.id = :estado order by cid.nome");
        q.setParameter("estado", id);
        List<Cidades> lista = q.list();
        s.close();
        return lista;
    }
    
    public Cidades pesquisaCidadesPorNome(String nome, Integer id) {
        Session s = HibernateUtil.getSession();
        Query q = s.createQuery("from Cidades cid where cid.nome like :cidade and cid.estado.id = :estado order by cid.nome");
        q.setParameter("cidade", nome);
        q.setParameter("estado", id);
        Cidades c = (Cidades) q.uniqueResult();
        s.close();
        return c;
    }
        
    public Integer contaCapital(Integer id){
        Session s = HibernateUtil.getSession();
        Query q = s.createQuery("select count(*) from Cidades cid where cid.estado.id = :estado and cid.capital = 1");
        q.setParameter("estado", id);
        Integer i = ((Long) q.uniqueResult()).intValue();
        s.close();
        return i;
    }
}

