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

/**
 *
 * @author bdurand
 */
public class EmployeDAO{
    
    public static void persist(Employe emp){
        EntityManager em = jpaUtil.obtenirEntityManager();
        em.persist(emp);
    }
    
    public static void update(Employe emp){
        EntityManager em = jpaUtil.obtenirEntityManager();
        em.merge(emp);
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
}
