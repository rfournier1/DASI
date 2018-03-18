/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import om.Medium;
import om.Voyant;
import om.Astrologue;
import om.Tarologue;

/**
 * 
 * @author bdurand
 */

public class MediumDAO {
    
    public static void persist(Medium m){
        EntityManager em = jpaUtil.obtenirEntityManager();
        em.persist(m);
    }
    
    public static void update(Medium m){
        EntityManager em = jpaUtil.obtenirEntityManager();
        em.merge(m);
    }
    
    public static void delete(Medium m){
        EntityManager em = jpaUtil.obtenirEntityManager();
        em.remove(m);
    }
    
    public static Medium find(Long id, Medium.Talent t){
        EntityManager em = jpaUtil.obtenirEntityManager();
        if(t.equals(Medium.Talent.Voyant)){
            return em.find(Voyant.class, id);
        }else if(t.equals(Medium.Talent.Tarologue)){
            return em.find(Tarologue.class, id);
        }else if(t.equals(Medium.Talent.Astrologue)){
            return em.find(Astrologue.class, id);
        }
        return null;
    }

    public static List<Medium> getAllMediumsByTalent(List<Medium.Talent> list){
        ArrayList<Medium> res = new ArrayList<Medium>();
        EntityManager em = jpaUtil.obtenirEntityManager();
        if(list.contains(Medium.Talent.Astrologue))
        {
            Query query = em.createQuery("Select a from Astrologue a");
            res.addAll(query.getResultList());
        }
        if(list.contains(Medium.Talent.Tarologue))
        {
            Query query = em.createQuery("Select t from Tarologue t");
            res.addAll(query.getResultList());
        }
        if(list.contains(Medium.Talent.Voyant))
        {
            Query query = em.createQuery("Select v from Voyant v");
            res.addAll(query.getResultList());
        }
        
        return res;
    }
//    public static List<Medium> getMediumByTalent(Medium.Talent talent){
//        EntityManager em = jpaUtil.obtenirEntityManager();
//        Query query = em.createQuery("Select m from Client c where c.identifiant = :id"); 
//        query.setParameter("id", id);
//        return query.getResultList();
//    }
}