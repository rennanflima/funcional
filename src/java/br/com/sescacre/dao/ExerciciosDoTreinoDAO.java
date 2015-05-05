/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sescacre.dao;

import br.com.sescacre.entidades.Exercicios;
import br.com.sescacre.entidades.ExerciciosDoTreino;
import br.com.sescacre.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Rennan Francisco
 */
public class ExerciciosDoTreinoDAO {

    public void salvar(Exercicios et) {
        Session s = HibernateUtil.getSession();
        try {
            Transaction t = s.beginTransaction();
            s.save(et);
            t.commit();
        } catch (Exception e) {
            System.out.println("Erro ao salvar: " + e);
        } finally {
            s.close();
        }
    }

    public void alterar(ExerciciosDoTreino et) {
        Session s = HibernateUtil.getSession();
        try {
            Transaction t = s.beginTransaction();
            s.update(et);
            t.commit();
        } catch (Exception e) {
            System.out.println("Erro ao alterar: " + e);
        } finally {
            s.close();
        }
    }

    public void excluir(ExerciciosDoTreino et) {
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        s.delete(et);
        t.commit();
        s.close();
    }

    public List<ExerciciosDoTreino> ListaTodos() {
        Session s = HibernateUtil.getSession();
        Query q = s.createQuery("from ExerciciosTreino");
        List<ExerciciosDoTreino> lista = q.list();
        s.close();
        return lista;
    }

    public ExerciciosDoTreino pesquisaPorId(Integer id) {
        Session s = HibernateUtil.getSession();
        ExerciciosDoTreino ext = (ExerciciosDoTreino) s.load(ExerciciosDoTreino.class, id);
        s.close();
        return ext;
    }

    public List<ExerciciosDoTreino> pesquisaExercicioTreinoPorExercicio(Integer id) {//join
        Session s = HibernateUtil.getSession();
        Query q = s.createQuery("from ExerciciosTreino et where et.exercicio.id = :exercicio order by et.nome");
        q.setParameter("exercicio", id);
        List<ExerciciosDoTreino> lista = q.list();
        s.close();
        return lista;
    }
}
