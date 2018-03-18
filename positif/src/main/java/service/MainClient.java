/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.jpaUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import om.Client;
import om.Medium;
import om.Voyance;
import static service.Service.demanderVoyance;
import static service.Service.getHistorique;
import static service.Service.getMedium;
import static service.Service.getVoyanceEnCours;
import static service.Service.identificationClient;
import static service.Service.inscriptionClient;
import static service.Service.rechercheMediums;
import static service.Service.terminerVoyance;
import util.Saisie;

/**
 *
 * @author Michel
 */
public class MainClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        jpaUtil.init();
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date d = new Date();
        try{
            d = sdf.parse("21/12/2012");
        }catch(ParseException e){
            e.printStackTrace();
        }
        Client c1 = new Client(Client.Civilite.Mr, "Doe", "John", d, "mailJo", "adress", "tel", "Jo", "password");
        Client c2 = new Client(Client.Civilite.Mr, "Doe", "John", d, "mailJoh", "adress", "tel", "Joh", "pass");
        Client c3 = new Client(Client.Civilite.Mr, "Doe", "John", d, "mailJohn", "adress", "tel", "John", "word");
        
        inscriptionClient(c1);
        inscriptionClient(c2);
        inscriptionClient(c3);
        
        String in = "go";
        ArrayList<String> list = new ArrayList<String>();
        int choix = 0;
        System.out.println("vous êtes un client");
        while(true){
            Client c = null;
            while(choix != 3){
                choix = Saisie.lireInteger("tapez 1 pour vous log in, \rtaper 2 pour vous inscrire, \rtaper 3 pour quitter", Arrays.asList(1,2,3));
                switch(choix){
                    case 1 :
                        list.add(Saisie.lireChaine("id : "));
                        list.add(Saisie.lireChaine("mdp : "));
                        c = identificationClient(list.get(0),list.get(1));
                        System.out.println(c);
                        break;
                    case 2 :
                        Client.Civilite civ = Client.Civilite.intToCivilite(Saisie.lireInteger("civilité, 1 M, 2 Mme, 3 autre",Arrays.asList(1,2,3)));
                        list.add(Saisie.lireChaine("nom : "));
                        list.add(Saisie.lireChaine("prénom : "));
                        list.add(Saisie.lireChaine("date de naissance(dd/mm/yyyy) : "));
                        list.add(Saisie.lireChaine("mail : "));
                        list.add(Saisie.lireChaine("adresse : "));
                        list.add(Saisie.lireChaine("téléphone : "));
                        list.add(Saisie.lireChaine("identifiant : "));
                        list.add(Saisie.lireChaine("mot de passe : "));
                        try{
                            d = sdf.parse(list.get(2));
                        }catch(ParseException e){
                            e.printStackTrace();
                        }
                        c = new Client(civ,list.get(0),list.get(1),d,list.get(3),list.get(4),list.get(5),list.get(6),list.get(7));
                        if(inscriptionClient(c) == 0)
                            c = null;
                }
                if(choix != 3 && c == null)
                    System.out.println("échec"+c);
                else
                    break;
            }
            if(choix == 3)
                break;
            
            System.out.println("Vous êtes log in");
            while(true){
                choix = Saisie.lireInteger("tapez 1 pour vous log out, \rtapez 2 pour voir votre profil, \rtapez 3 pour voir la liste des mediums, \rtapez 4 pour voir votre historique, \rtapez 5 pour demander une voyance", Arrays.asList(1,2,3,4,5));
                if(choix == 1)
                    break;
                switch(choix){
                    case 2 :
                        System.out.println(c);
                        break;
                    case 3 :
                        ArrayList<Medium.Talent> listTal = new ArrayList<Medium.Talent>();
                        System.out.println("Quels talents ?");
                        for(int i=0; i<3; i++){
                            int choixTal = Saisie.lireInteger("1 voyants, 2 tarologue, 3 astrologue, 4 stop", Arrays.asList(1,2,3,4));
                            if(choixTal == 4)
                                break;
                            listTal.add(Medium.Talent.intToTalent(choixTal));
                        }
                        for(Medium m : rechercheMediums(listTal))
                        {
                            System.out.println(m.getId()+" "+m.getTalent()+" "+m.getPrenom()+" "+m.getNom()+" infos : "+m.getInfos());
                        }
                        while(true){
                            int voir = Saisie.lireInteger("Voir le profil d'un médium ? 1 oui, 2 non", Arrays.asList(1,2));
                            if(voir == 2)
                                break;
                            String id = Saisie.lireChaine("id : ");
                            int tal = Saisie.lireInteger("Quel talent ? \r1 voyants, 2 tarologue, 3 astrologue", Arrays.asList(1,2,3));
                            Medium voirM = getMedium(Long.valueOf(id).longValue(), Medium.Talent.intToTalent(tal));
                            if(voirM == null)
                                System.out.println("échec");
                            else{
                                System.out.println(voirM.getId()+" "+voirM.getTalent()+" "+voirM.getPrenom()+" "+voirM.getNom()+" infos : "+voirM.getInfos());
                                System.out.println(voirM.getBio());
                            }
                        }
                        break;
                    case 4 :
                        for(Voyance v : getHistorique(c)){
                            System.out.println(v.toStringClient());
                        }
                        break;
                    case 5 :
                        System.out.println("Indiquez le medium");
                        String id = Saisie.lireChaine("id : ");
                        int tal = Saisie.lireInteger("Quel talent ? \r1 voyants, 2 tarologue, 3 astrologue", Arrays.asList(1,2,3));
                        Medium voyM = getMedium(Long.valueOf(id).longValue(), Medium.Talent.intToTalent(tal));
                        demanderVoyance(c, voyM);
                        while(true){
                            Voyance currentV = getVoyanceEnCours(c);
                            if(currentV == null){
                                System.out.println("abort");
                                break;
                            }
                            System.out.println("Status de votre demande :");
                            System.out.println(currentV.toStringClient());
                            int attente = Saisie.lireInteger("1 refresh, 2 abandonner la demande", Arrays.asList(1,2));
                            switch(attente){
                                case 1 :
                                    continue;
                                case 2 :
                                    terminerVoyance(currentV, "abandonClient");
                                    break;
                            }
                            if(attente == 2)
                                break;
                        }
                }
            }
            System.out.println("vous êtes log out");
        }
    }
    
}
