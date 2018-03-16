/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import om.Employe;
import om.Voyance;

/**
 *
 * @author bdurand 
 */
public class VoyanceDAO {
    
    public static void persist(Voyance v){
        EntityManager em = jpaUtil.obtenirEntityManager();
        em.persist(v);
    }
    
    public static void update(Voyance v){
        EntityManager em = jpaUtil.obtenirEntityManager();
        em.merge(v);
    }
    
    public static void delete(Voyance v){
        EntityManager em = jpaUtil.obtenirEntityManager();
        em.remove(v);
    }
    
    public static List<Voyance> getVoyancesByEmploye(Employe e){
        EntityManager em = jpaUtil.obtenirEntityManager();
        Query query = em.createQuery("Select v from Voyance v where v.employe = :emp");
        query.setParameter("emp", e);
        return query.getResultList();
    }
}
