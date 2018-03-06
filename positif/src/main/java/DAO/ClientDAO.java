/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import javax.persistence.EntityManager;
import om.Client;

/**
 *
 * @author bdurand
 */ 
public class ClientDAO {
    
    public static void persist(Client c){
        EntityManager em = jpaUtil.obtenirEntityManager();
        em.persist(c);
    }
    
    public static Client update(Client c){
        EntityManager em = jpaUtil.obtenirEntityManager();
        return em.merge(c);
    }
    
    public static void delete(Client c){
        EntityManager em = jpaUtil.obtenirEntityManager();
        em.remove(c);
    }
    
    public static Client find(String id){
        EntityManager em = jpaUtil.obtenirEntityManager();
        return em.find(Client.class, id);
        
    }
}
