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
import om.Medium;
import util.AstroTest;

/**
 *
 * @author bdurand
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        jpaUtil.init();
        Initialisation();
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date d = new Date();
        try{
            d = sdf.parse("21/12/2012");
        }catch(ParseException e){
            e.printStackTrace();
        }
        
        Client c1 = new Client(Client.Civilite.Mr, "Doe", "John", d, "mail", "adress", "tel", "Jo", "password");
        
        try {
            System.out.println(InscriptionClient(c1));
        }catch(IOException ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        jpaUtil.creerEntityManager();
        Client c = ClientDAO.find(new Long(4));
        Medium v = MediumDAO.find(new Long(2), Medium.Talent.Voyant);
        Employe e = EmployeDAO.find(new Long(1));
        System.out.println(v);
//        System.out.println(v.getSupport());
        System.out.println(c);
        System.out.println(e);
        Voyance voy1 = new Voyance(c,e,v);
        System.out.println(voy1);
        jpaUtil.fermerEntityManager();
        jpaUtil.creerEntityManager();
        VoyanceDAO.persist(voy1);
      //  Voyance voy = getVoyance(e);
     //   System.out.println(voy);
//        List<Voyance> list = ClientDAO.getHistoriqueByClient(c);
//        System.out.println(list);
        jpaUtil.fermerEntityManager();
        ArrayList<Medium.Talent> l = new ArrayList<>();
        l.add(Medium.Talent.Voyant);
        System.out.println(rechercheMediums(l));
    }
    public static void Initialisation(){
        Voyant v1 = new Voyant("Irma","Mme",Voyant.Support.MarcDeCafe,"LA fameuse");
        Voyant v2 = new Voyant("Michelle","Mme",Voyant.Support.MarcDeCafe,"LA fameuse");
        
        Employe e1 = new Employe();
        
        
        
        jpaUtil.creerEntityManager();
        jpaUtil.ouvrirTransaction();
        EmployeDAO.persist(e1);    
        MediumDAO.persist(v1);
        MediumDAO.persist(v2);
        jpaUtil.validerTransaction();
        jpaUtil.fermerEntityManager();
    }
    
    public static int InscriptionClient(Client c) throws IOException{
        jpaUtil.creerEntityManager();
        jpaUtil.ouvrirTransaction();
        AstroTest astro = new AstroTest(AstroTest.MA_CLÉ_ASTRO_API);
        c.setProfilAstro(astro.getProfil(c.getPrenom(), c.getDate()));
        try{
            ClientDAO.persist(c);
            jpaUtil.validerTransaction();
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
        jpaUtil.fermerEntityManager();    
        return 1;
    }
    
    public static Client IdentificationClient(String identifiant, String mdp){
        jpaUtil.creerEntityManager();
        List<Client> list = ClientDAO.getClientByIdentifiant(identifiant);
        jpaUtil.fermerEntityManager();
        System.out.println(list);
        if(!list.isEmpty()){
            if(list.get(0).getMdp() == mdp){
                return list.get(0);
            }
        }
        return null;
    }
    
    public static Employe IdentificationEmploye(String identifiant, String mdp){
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
    
    public static void getHistorique(Client c){
        jpaUtil.creerEntityManager();
        List<Voyance> list = ClientDAO.getHistoriqueByClient(c);    
        jpaUtil.fermerEntityManager();
        System.out.println(list);
    }
    
    public static List<Medium> rechercheMediums(List<Medium.Talent> talents){
        jpaUtil.creerEntityManager();
        List<Medium> res = MediumDAO.getAllMediumsByTalent(talents);
        jpaUtil.fermerEntityManager();
        return res;
    }
    
    public static void demanderVoyance(Client c){
        
    }
    
    public static Voyance getVoyance(Employe e){
        jpaUtil.creerEntityManager();
        List<Voyance> res = VoyanceDAO.getVoyancesByEmploye(e);
        jpaUtil.fermerEntityManager();
        return res.get(0);
    }
    
    public static void trouverClientParVoyance(Voyance v){
        
    }
    
    public static void getStats(Employe e){
        
    }
}
