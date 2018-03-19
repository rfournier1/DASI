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
import om.Client;
import om.Voyance;
import dao.VoyanceDAO;
import java.util.ArrayList;
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
    public static void iniJpaUtil(){
        jpaUtil.init();
    }
    public static void main(String[] args) {
        
        jpaUtil.init();
        initialisation();
    }
    public static void initialisation(){
        
        Voyant v1 = new Voyant("Irma","Mme",Voyant.Support.BouleDeCristal,"LA fameuse");
        Voyant v2 = new Voyant("Michelle","Mme",Voyant.Support.MarcDeCafe,"Vous aidera a retrouver votre chat");
        Voyant v3 = new Voyant("Gitane","La Vieille",Voyant.Support.MarcDeCafe,"On dit qu'elle a un jour permis à un berger andaloux de trouver un trésor");
        Astrologue a1 = new Astrologue("Attan","Charles","INSANE Lyon","59","Major de promo !");
        Astrologue a2 = new Astrologue("Valpierre","Artis","ONDINE","1","La tête dans les étoiles, pour mieux vous servir");
        Tarologue t1 = new Tarologue("Nil'Erg","Duom",Tarologue.Cartes.Marseille,"Ecoute les cartes avec sagesse");
        Tarologue t2 = new Tarologue("Le Chanteur","Merlin",Tarologue.Cartes.Broceliande,"Autodidacte surprenant, il a un vrai don pour le tarot");
        Collection<Medium> col = new ArrayList<Medium>();
        col.add(a1);
        Collection<Medium> col1 = new ArrayList<Medium>();        
        col1.add(v1);
        col1.add(v2);
        col1.add(v3);
        col1.add(a2);
        col1.add(t1);
        col1.add(t2);
        Collection<Medium> col2 = new ArrayList<Medium>();
        col2.add(a2);
        col2.add(t2);
        Collection<Medium> col3 = new ArrayList<Medium>();
        col3.add(t1);
        col3.add(v3);
        Collection<Medium> col4 = new ArrayList<Medium>();
        col4.add(v2);
        col4.add(t1);
        col4.add(a2);    
        Employe e1 = new Employe("Doe","Jonathan","jdoe1","azerty",col);
        Employe e2 = new Employe("Doe","Joseph","jdoe2","azerty",col1);
        Employe e3 = new Employe("Doe","Jotaro","jdoe3","azerty",col2);
        Employe e4 = new Employe("Doe","Josuke","jdoe4","azerty",col2);
        Employe e5 = new Employe("Doe","Giorno","jdoe5","azerty",col3);
        Employe e6 = new Employe("Doe","Jolyne","jdoe6","azerty",col3);
        Employe e7 = new Employe("Doe","Giorno","jdoe7","azerty",col4);
        Employe e8 = new Employe("Doe","Johnny","jdoe8","azerty",col4);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date d = new Date();
            Date d2 = new Date();
            Date d3 = new Date();
            Date d4 = new Date();
                 
        try{
             d = sdf.parse("21/12/2012");
             d2= sdf.parse("28/08/1992");
             d3=sdf.parse("10/07/1976");
             d4=sdf.parse("17/02/1996");
        }catch(ParseException e){
        e.printStackTrace();
        }
        Client c1 = new Client(Client.Civilite.Mr, "Doe", "John", d, "mailJo", "adress", "tel", "Jo", "password");
        Client c2 = new Client(Client.Civilite.Mr, "Doe", "John", d, "mailJoh", "adress", "tel", "Joh", "pass");
        Client c3 = new Client(Client.Civilite.Mr, "Doe", "John", d, "mailJohn", "adress", "tel", "John", "word");
        Client c4 = new Client(Client.Civilite.Mr, "BORROTI","Raphael",d2,"rborrotimatiasdantas4171@free.fr","8 Rue Arago, Villeurbanne","0328178508","mdantas","pass"); 
        Client c5 = new Client(Client.Civilite.Mme,"OLMEADA","Nor",d2,"nolmeadamarais1551@gmail.com","5 Rue Léon Fabre, Villeurbanne","0418932546","nolmeda","pass");
        Client c6 = new Client(Client.Civilite.Mme,"RAYES","Olena",d3,"orayesgemez5313@outlook.com","12 Rue de la Prevoyance, Villeurbanne","0532731620","orayes","pass");
        Client c7 = new Client(Client.Civilite.Mr,"WOAGNER","Moez",d3,"moez.woagner@laposte.net","6 Rue Camille Koechlin, Villeurbanne","0832205629 ","mwagner","pass");
        Client c8 = new Client(Client.Civilite.Mr,"HONRY","Matteo",d4," matteo.honry@yahoo.com","9 Impasse Guillet, Villeurbanne","0482381862","mhonry","pass");
        inscriptionClient(c1);
        inscriptionClient(c2);
        inscriptionClient(c3);   
        inscriptionClient(c4);
        inscriptionClient(c5);
        inscriptionClient(c6);
        inscriptionClient(c7);
        inscriptionClient(c8);
//        Voyance voy2 = new Voyance(c1,v1);
//        voy2.assignEmploye(e1);  
//        Voyance voy3 = new Voyance(c1,v2);
//        voy3.assignEmploye(e1);
//        Voyance voy4 = new Voyance(c1,v1);
//        voy4.assignEmploye(e2);
        

        try{
            jpaUtil.creerEntityManager();
            jpaUtil.ouvrirTransaction();  
            EmployeDAO.persist(e1);  
            EmployeDAO.persist(e2);  
            EmployeDAO.persist(e3);  
            EmployeDAO.persist(e4);  
            EmployeDAO.persist(e5);    
            EmployeDAO.persist(e6); 
            EmployeDAO.persist(e7); 
            EmployeDAO.persist(e8); 
            MediumDAO.persist(v1);
            MediumDAO.persist(v2);
            MediumDAO.persist(v3);
            MediumDAO.persist(t1);
            MediumDAO.persist(t2);
            MediumDAO.persist(a1);
            MediumDAO.persist(a2);
            jpaUtil.validerTransaction();
//            jpaUtil.ouvrirTransaction();
//            VoyanceDAO.persist(voy1);
//            VoyanceDAO.persist(voy2);
//            VoyanceDAO.persist(voy3);
//            VoyanceDAO.persist(voy4);
//            jpaUtil.validerTransaction();
            jpaUtil.fermerEntityManager();
            System.out.println(demanderVoyance(c1,a1));
//            accepterVoyance(voy1);
//            terminerVoyance(voy1,"");
//            accepterVoyance(voy4);
//            terminerVoyance(voy4,"client naif");
//            
            
        }catch(Exception e){
            e.printStackTrace();
        }
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
        Employe e = EmployeDAO.getEmployeByIdentifiant(identifiant);
        jpaUtil.fermerEntityManager();
        if(e!=null){
            if(e.getMdp().equals(mdp)){
                return e;
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
    
    public static Medium getMedium(Long id, Medium.Talent t){
        jpaUtil.creerEntityManager();
        Medium res = MediumDAO.find(id, t);
        jpaUtil.fermerEntityManager();
        return res;
    }
    
    public static int demanderVoyance(Client c, Medium m){
        if(getVoyanceEnCours(c) != null)
            return 0;
        jpaUtil.creerEntityManager();
        jpaUtil.ouvrirTransaction();
        Voyance v = new Voyance(c, m);
        Employe idle = EmployeDAO.getIdleEmploye(m);
        if(idle == null)
            return 0;
        idle.setStatus(false);
        v.assignEmploye(idle);
        EmployeDAO.update(idle);
        VoyanceDAO.persist(v);
        jpaUtil.validerTransaction();
        jpaUtil.fermerEntityManager();
        return 1;
    }
    
    public static Voyance getVoyanceEnCours(Client c){
        List<Voyance> voy = getHistorique(c);
        for(Voyance v : voy){
            if(v.getStatus() != Voyance.Status.Termine)
                return v;
        }
        return null;
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
    
    public static List<String> genererPredictions(Client c, int amour, int sante, int travail){
        AstroTest astro = new AstroTest(AstroTest.MA_CLÉ_ASTRO_API);
        List<String> result = new ArrayList<>();
        try{
            result = astro.getPredictions(c.getCouleur(),c.getAnimalTotem(),amour,sante,travail);
        }catch(IOException ex){
            ex.printStackTrace();
            return null;
        }
        return result;
    }
    
    public static Employe mergeEmploye(Employe e){
        jpaUtil.creerEntityManager();
        e=EmployeDAO.find(e.getId());
        jpaUtil.fermerEntityManager();
        return e;        
    }
}
