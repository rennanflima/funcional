/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sescacre.dao;

import br.com.sescacre.entidades.CargosDoSetor;
import br.com.sescacre.entidades.Funcionarios;
import br.com.sescacre.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Rennan Francisco
 */
public class FuncionarioDAO {
/*
    public void salvar(CargosDoSetor cds) {
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        s.save(cds);
        t.commit();
        s.close();
    }

    public void alterar(CargosDoSetor cds) {
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        s.update(cds);
        t.commit();
        s.close();
    }

    public void excluir(CargosDoSetor cds) {
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        s.delete(cds);
        t.commit();
        s.close();
    }

    public List<CargosDoSetor> ListaTodos() {
        Session s = HibernateUtil.getSession();
        Query q = s.createQuery("from CargosDoSetor");
        List<CargosDoSetor> lista = q.list();
        s.close();
        return lista;
    }

    public CargosDoSetor pesquisaPorId(Integer id) {
        Session s = HibernateUtil.getSession();
        CargosDoSetor cds = (CargosDoSetor) s.load(CargosDoSetor.class, id);
        s.close();
        return cds;
    }
*/
    public List<Funcionarios> pesquisaFuncionarioPorCargoDoSetor(Integer id) {//join
        Session s = HibernateUtil.getSession();
        Query q = s.createQuery("from Funcionarios f where f.cargo.id = :cargo order by f.id");
        q.setParameter("cargo", id);
        List<Funcionarios> lista = q.list();
        s.close();
        return lista;
    }
    
}
