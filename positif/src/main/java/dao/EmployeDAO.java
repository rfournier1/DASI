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

    public static Employe getEmployeByIdentifiant(String id){
        EntityManager em = jpaUtil.obtenirEntityManager();
        Query query = em.createQuery("Select e from Employe e where e.identifiant = :id"); 
        query.setParameter("id", id);
        List<Employe> list = query.getResultList();
        if(!list.isEmpty())
            return list.get(0);
        else 
            return null;
    }
    
    public static Employe getIdleEmploye(Medium m){
        EntityManager em = jpaUtil.obtenirEntityManager();
        Query query = em.createNativeQuery("Select e.id from Employe e where not exists(select v.E_ID from Voyance v where v.E_ID = e.id)");
        List<Object> list = query.getResultList();
        for(Object o : list){
                Employe res = find((Long)o);
                if(res.getMediumsPossibles().contains(m)){
                    System.out.println(res);
                    return res;
                }
            }
        System.out.println(list);

        query = em.createNativeQuery("Select v.E_ID from Voyance v group by v.E_ID order by count(*) asc"); 
        list = query.getResultList();

        System.out.println(list);
        if(!list.isEmpty()){
            for(Object o : list){
                Employe res = find((Long)o);
                if(res.getStatus()){
                    if(res.getMediumsPossibles().contains(m)){
                        System.out.println(res);
                        return res;
                    }
                }
            }
        }
        return null;
    }
}
