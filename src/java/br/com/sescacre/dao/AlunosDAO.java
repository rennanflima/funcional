/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sescacre.dao;

import br.com.sescacre.entidades.Alunos;
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
public class AlunosDAO {

    public void salvar(Alunos a) throws Exception {
        Session s = HibernateUtil.getSession();
        try {
            Transaction t = s.beginTransaction();
            s.save(a);
            t.commit();
        } catch (Exception ex) {
            throw new SQLIntegrityConstraintViolationException();
        } finally {
            s.close();
        }
    }

    public void alterar(Alunos a) throws Exception {
        Session s = HibernateUtil.getSession();
        try {
            Transaction t = s.beginTransaction();
            s.update(a);
            t.commit();
        } catch (Exception e) {
            throw new SQLIntegrityConstraintViolationException();
        } finally {
            s.close();
        }
    }

    public void excluir(Alunos a) {
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        s.delete(a);
        t.commit();
        s.close();
    }

    public List<Alunos> ListaTodos() {
        Session s = HibernateUtil.getSession();
        Query q = s.createQuery("from Alunos");
        List<Alunos> lista = q.list();
        s.close();
        return lista;
    }

    public Alunos pesquisaPorId(Integer id) {
        Session s = HibernateUtil.getSession();
        Alunos a = (Alunos) s.load(Alunos.class, id);
        s.close();
        return a;
    }
    
    public Alunos pesquisaAlunoPorCPF(String cpf){
        Session s = HibernateUtil.getSession();
        Query q = s.createQuery("from Alunos a where a.cpf = :cpf order by a.nome");
        q.setParameter("cpf", cpf);
        Alunos a = (Alunos) q.uniqueResult();
        s.close();
        return a;
    }
}
