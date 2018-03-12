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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import om.Client;
import om.Voyance;

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
        jpaUtil.creerEntityManager();
        Employe e1 = new Employe("José","Bové");
        Voyant v1 = new Voyant("Irma","Mme",Voyant.Support.MarcDeCafe,"LA fameuse");
        jpaUtil.ouvrirTransaction();
        EmployeDAO.persist(e1);    
        MediumDAO.persist(v1);
        jpaUtil.validerTransaction();
        jpaUtil.fermerEntityManager();
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date d = new Date();
        try{
            d = sdf.parse("21/12/2012");
        }catch(ParseException e){
            e.printStackTrace();
        }
        
        Client c = new Client(Client.Civilite.Mr, "Doe", "John", d, "mail", "adress", "tel", "Jo", "password");
        
        //InscriptionClient(c);
        Client john = IdentificationClient("Jo", "password");
        
        System.out.println(john);
        
    }
    
    public static void InscriptionClient(Client c){
        jpaUtil.creerEntityManager();
        jpaUtil.ouvrirTransaction();
        ClientDAO.persist(c);
        jpaUtil.validerTransaction();
        jpaUtil.fermerEntityManager();        
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
    
    public static void getHistorique(Client c){
        
    }
    
    public static void rechercheMedium(){
        
    }
    
    public static void demanderVoyance(Client c){
        
    }
    
    public static void getVoyance(Employe e){
        
    }
    
    public static void trouverClientParVoyance(Voyance v){
        
    }
    
    public static void getStats(Employe e){
        
    }
}
