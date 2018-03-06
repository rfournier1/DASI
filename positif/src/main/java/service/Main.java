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
import java.util.Date;
import om.Client;

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
    }
    
    public void InscriptionClient(Client.Civilite civilite, String nom, String prenom, String date, String email, String adresse, String tel, String identifiant, String mdp) throws ParseException{
        Client c = new Client(civilite, nom, prenom, date, email, adresse, tel, identifiant, mdp);
        jpaUtil.ouvrirTransaction();
        ClientDAO.persist(c);
        jpaUtil.validerTransaction();
        jpaUtil.fermerEntityManager();        
    }
    
    public Client Identification(String identifiant, String mdp){
        Client c = ClientDAO.find(identifiant);
        if(c !=null){
            if(mdp.equals(c.getMdp())){
                return c;
            }
        }
        return null;
    }
}
