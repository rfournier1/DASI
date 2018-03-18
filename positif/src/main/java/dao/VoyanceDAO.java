/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import om.Employe;
import om.Medium;
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
    
    public static Voyance update(Voyance v){
        EntityManager em = jpaUtil.obtenirEntityManager();
        return em.merge(v);
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
    public static HashMap<Medium,Long> getStatsMedium(){
    HashMap<Medium,Long> stats = new HashMap<Medium,Long>();
    EntityManager em = jpaUtil.obtenirEntityManager();
    Query mQuery = em.createQuery("Select v.medium from Voyance v where v.status= :state group by v.medium order by count(v.employe) asc");
    Query cQuery = em.createQuery("Select count(v.employe) from Voyance v where v.status= :state group by v.medium order by count(v.employe) asc");
    mQuery.setParameter("state",Voyance.Status.Termine);
    cQuery.setParameter("state",Voyance.Status.Termine);
    List<Medium> mediums =mQuery.getResultList();
    List<Long> counts = cQuery.getResultList();
    int i =0;
    for(Long c : counts){
        stats.put(mediums.get(i),c);
        i++;
    }
    return stats;
    }
    
    public static HashMap<Employe,Long> getStatsEmploye(){
        HashMap<Employe,Long> stats = new HashMap<Employe,Long>();
        EntityManager em= jpaUtil.obtenirEntityManager();
        Query eQuery = em.createQuery("Select v.employe from Voyance v where v.status= :state group by v.employe order by count(v.medium) asc");
        Query cQuery = em.createQuery("Select count(v.medium) from Voyance v where v.status= :state group by v.employe order by count(v.medium) asc");
        eQuery.setParameter("state",Voyance.Status.Termine);
        cQuery.setParameter("state",Voyance.Status.Termine);
        List<Employe> employes =eQuery.getResultList();
        List<Long> counts = cQuery.getResultList();
        int i =0;
        for(Long c : counts){
            stats.put(employes.get(i),c);
            i++;
        }
        System.out.println(stats);
        return stats;
    }
}
    
