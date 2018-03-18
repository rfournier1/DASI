/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ClientDAO;
import dao.EmployeDAO;
import dao.jpaUtil;
import om.Employe;
import om.Voyant;
import dao.MediumDAO;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityExistsException;
import javax.persistence.Query;
import om.Client;
import om.Medium;
import om.Voyance;
import dao.VoyanceDAO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import javafx.util.Pair;
import om.Astrologue;
import om.Medium;
import om.Tarologue;
import util.AstroTest;
import util.Saisie;

/**
 *
 * @author bdurand
 */
public class Service {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        jpaUtil.init();
        initialisation();

//        jpaUtil.creerEntityManager();
//        Employe e = EmployeDAO.find(new Long(1));
//        Client c = ClientDAO.getClientByIdentifiant("Jo");
//        System.out.println("emp e : "+e);
//        jpaUtil.fermerEntityManager();
//        
//        List<Voyance> voy = getVoyance(e);
//        System.out.println("voyance voy : " + voy);
//        
//        List<Voyance> list = getHistorique(c);
//        System.out.println(list);
//        ArrayList<Medium.Talent> l = new ArrayList<>();
//        l.add(Medium.Talent.Voyant);
//        List<Medium> listM = rechercheMediums(l);
//        System.out.println(listM);
//        demanderVoyance(c, listM.get(0));
//        accepterVoyance(list.get(0));
//        System.out.println(getHistorique(c));
//        jpaUtil.creerEntityManager();
//        Employe e = EmployeDAO.find(new Long(2));
//        System.out.println(e);
//        jpaUtil.fermerEntityManager();
//        getStats();
        
//        List<Voyance> voy = getAllVoyance(e);
//        System.out.println("voyance voy : " + voy);
    }
    public static void initialisation(){
        Voyant v1 = new Voyant("Irma","Mme",Voyant.Support.BouleDeCristal,"LA fameuse");
        Voyant v2 = new Voyant("Michelle","Mme",Voyant.Support.MarcDeCafe,"Vous aidera a retrouver votre chat");
        Voyant v3 = new Voyant("Gitane","La Vieille",Voyant.Support.MarcDeCafe,"On dit qu'elle a un jour permis à un berger andaloux de trouver un trésor");
        Astrologue a1 = new Astrologue("Attan","Charles","INSANE Lyon","59","Major de promo !");
        Astrologue a2 = new Astrologue("Valpierre","Artis","ONDINE","1","La tête dans les étoiles, pour mieux vous servir");
        Tarologue t1 = new Tarologue("Nil'Erg","Duom",Tarologue.Cartes.Marseille,"Ecoute les cartes avec sagesse");
        Tarologue t2 = new Tarologue("Le Chanteur","Merlin",Tarologue.Cartes.Broceliande,"Autodidacte surprenant, il a un vrai don pour le tarot");
//        Employe e0 = new Employe();
        Collection<Medium> col = new ArrayList<Medium>();
        col.add(a1);
        col.add(v1);
        col.add(v2);
        col.add(v3);
        col.add(a2);
        col.add(t1);
        col.add(t2);
        
        Employe e1 = new Employe("Doe","Jonathan","jdoe1","azerty",col);
        Employe e2 = new Employe("Doe","Joseph","jdoe2","azerty",col);
        Employe e3 = new Employe("Doe","Jotaro","jdoe3","azerty",col);
        Employe e4 = new Employe("Doe","Josuke","jdoe4","azerty",col);
        Employe e5 = new Employe("Doe","Jolyne","jdoe5","azerty",col);
        jpaUtil.creerEntityManager();
        jpaUtil.ouvrirTransaction();
//        EmployeDAO.persist(e0);  
        EmployeDAO.persist(e1);  
        EmployeDAO.persist(e2);  
        EmployeDAO.persist(e3);  
        EmployeDAO.persist(e4);  
        EmployeDAO.persist(e5);    
        MediumDAO.persist(v1);
        MediumDAO.persist(v2);
        MediumDAO.persist(v3);
        MediumDAO.persist(t1);
        MediumDAO.persist(t2);
        MediumDAO.persist(a1);
        MediumDAO.persist(a2);
        jpaUtil.validerTransaction();
        jpaUtil.ouvrirTransaction();
//        Voyance voy1 = new Voyance(c1,v1);
//        voy1.assignEmploye(e1);
//        Voyance voy2 = new Voyance(c1,v1);
//        voy2.assignEmploye(e1);  
//        Voyance voy3 = new Voyance(c1,v2);
//        voy3.assignEmploye(e1);
//        Voyance voy4 = new Voyance(c1,v1);
//        voy4.assignEmploye(e2);
//        VoyanceDAO.persist(voy1);
//        VoyanceDAO.persist(voy2);
//        VoyanceDAO.persist(voy3);
//        VoyanceDAO.persist(voy4);        

        jpaUtil.validerTransaction();
        
        jpaUtil.fermerEntityManager();
    }
    
    public static int inscriptionClient(Client c){
        jpaUtil.creerEntityManager();
        jpaUtil.ouvrirTransaction();
        AstroTest astro = new AstroTest(AstroTest.MA_CLÉ_ASTRO_API);
        try{
            c.setProfilAstro(astro.getProfil(c.getPrenom(), c.getDate()));
            ClientDAO.persist(c);
            jpaUtil.validerTransaction();
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
        jpaUtil.fermerEntityManager();    
        return 1;
    }
    
    public static Client identificationClient(String identifiant, String mdp){
        jpaUtil.creerEntityManager();
        Client c = ClientDAO.getClientByIdentifiant(identifiant);
        jpaUtil.fermerEntityManager();
        if(c != null){
            if(c.getMdp().equals(mdp)){
                return c;
            }
        }
        return null;
    }
    
    public static Employe identificationEmploye(String identifiant, String mdp){
        jpaUtil.creerEntityManager();
        List<Employe> list = EmployeDAO.getEmployeByIdentifiant(identifiant);
        jpaUtil.fermerEntityManager();
        System.out.println(list);
        if(!list.isEmpty()){
            if(list.get(0).getMdp().equals(mdp)){
                return list.get(0);
            }
        }
        return null;
    }
    
    public static List<Voyance> getHistorique(Client c){
        jpaUtil.creerEntityManager();
        List<Voyance> list = ClientDAO.getHistoriqueByClient(c);    
        jpaUtil.fermerEntityManager();
        return list;
    }
    
    public static List<Medium> rechercheMediums(List<Medium.Talent> talents){
        jpaUtil.creerEntityManager();
        List<Medium> res = MediumDAO.getAllMediumsByTalent(talents);
        jpaUtil.fermerEntityManager();
        return res;
    }
    
    public static int demanderVoyance(Client c, Medium m){
        List<Voyance> voy = getHistorique(c);
        for(Voyance v : voy){
            if(v.getStatus() != Voyance.Status.Termine)
                return 0;
        }
        jpaUtil.creerEntityManager();
        jpaUtil.ouvrirTransaction();
        Voyance v = new Voyance(c, m);
        Employe idle = EmployeDAO.getIdleEmploye(m);
        idle.setStatus(false);
        v.assignEmploye(idle);
        EmployeDAO.update(idle);
        VoyanceDAO.persist(v);
        jpaUtil.validerTransaction();
        jpaUtil.fermerEntityManager();
        return 1;
    }
    
    public static List<Voyance> getAllVoyance(Employe e){
        jpaUtil.creerEntityManager();
        List<Voyance> res = VoyanceDAO.getVoyancesByEmploye(e);
        jpaUtil.fermerEntityManager();
        return res;
    }
    
    public static Voyance getVoyanceEnCours(Employe e){
        List<Voyance> all = getAllVoyance(e);
        for(Voyance v : all){
            if(v.getStatus() != Voyance.Status.Termine)
                return v;
        }
        return null;
    }
    
    public static void accepterVoyance(Voyance v){
        v.setStatus(Voyance.Status.EnCours);
        v.setDebut(new Date());
        jpaUtil.creerEntityManager();
        jpaUtil.ouvrirTransaction();
        VoyanceDAO.update(v);
        jpaUtil.validerTransaction();
        jpaUtil.fermerEntityManager();
    }
    

    public static Pair<HashMap<Medium,Long>,HashMap<Employe,Long>> getStats(){
        jpaUtil.creerEntityManager();
        Pair<HashMap<Medium,Long>,HashMap<Employe,Long>> stats = new Pair<>(VoyanceDAO.getStatsMedium(),VoyanceDAO.getStatsEmploye());
        jpaUtil.fermerEntityManager();
        return stats;
    }

    public static void terminerVoyance(Voyance v, String com){
        if(v.getStatus() != Voyance.Status.Termine){
            v.setStatus(Voyance.Status.Termine);
            v.setFin(new Date());
            v.setCom(com);
            Employe e = v.getEmploye();
            e.setStatus(true);
            jpaUtil.creerEntityManager();
            jpaUtil.ouvrirTransaction();
            VoyanceDAO.update(v);
            EmployeDAO.update(e);
            jpaUtil.validerTransaction();
            jpaUtil.fermerEntityManager();
        }
    }
}