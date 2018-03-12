/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import javax.persistence.EntityManager;
import om.Medium;

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
    
    public static List<Medium> getMediumByTalent(Medium.Talent talent){
        EntityManager em = jpaUtil.obtenirEntityManager();
        Query query = em.createQuery("Select m from Client c where c.identifiant = :id"); 
        query.setParameter("id", id);
        return query.getResultList();
    }
}