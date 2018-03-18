/*  
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import om.Employe;
import om.Medium;

/**
 *
 * @author bdurand
 */
public class EmployeDAO{
    
    public static void persist(Employe emp){
        EntityManager em = jpaUtil.obtenirEntityManager();
        em.persist(emp);
    }
    
    public static Employe update(Employe emp){
        EntityManager em = jpaUtil.obtenirEntityManager();
        return em.merge(emp);
    }
    
    public static void delete(Employe emp){
        EntityManager em = jpaUtil.obtenirEntityManager();
        em.remove(emp);
    }
    public static Employe find(Long id){
        EntityManager em = jpaUtil.obtenirEntityManager();
        return em.find(Employe.class, id);
    }        

    public static List<Employe> getEmployeByIdentifiant(String id){
        EntityManager em = jpaUtil.obtenirEntityManager();
        Query query = em.createQuery("Select e from Employe e where e.identifiant = :id"); 
        query.setParameter("id", id);
        return query.getResultList();
    }
    
    public static Employe getIdleEmploye(Medium m){
        EntityManager em = jpaUtil.obtenirEntityManager();
        Query query = em.createNativeQuery("Select e.id from Employe e MINUS select distinct v.E_ID from Voyance v");
        List<Object> list = query.getResultList();
        if(list.isEmpty()){
            query = em.createNativeQuery("Select v.E_ID from Voyance v group by v.E_ID order by count(*) asc fetch first 1 rows only"); 
            list = query.getResultList();
        }
            if(!list.isEmpty()){
            for(Object o : list){
                Employe res = find((Long)o);
                Collection<Medium> medPos = res.getMediumsPossibles();
                if(res.getStatus()){
                    for(Medium med : medPos){
                        if(m.equals(med)){
                            return res;
                        }
                    }
                }
            }
        }
        return null;
    }
}
