/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import javax.persistence.EntityManager;
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
}