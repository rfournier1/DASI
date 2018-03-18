/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javafx.util.Pair;
import om.Employe;
import om.Medium;
import om.Voyance;
import util.Saisie;
/**
 *
 * @author romain
 */
public class MainEmploye {
    public static void main(String[] args){
        Service.iniJpaUtil();
        System.out.println("bienvenue sur positif, terminal Employe");
        int choix=0;
        choix = Saisie.lireInteger("1 - connexion \r\n2 - quitter", Arrays.asList(1,2));
        while(true){
            if(choix == 2){
                break;
            }else{
                boolean identified = false;
                String id = "";
                String mdp="";
                Employe e=null;
                while(identified == false){
                    id=Saisie.lireChaine("id :");
                    mdp=Saisie.lireChaine("mdp :");

                    e=Service.identificationEmploye(id,mdp);
                    if(e==null){
                       choix=Saisie.lireInteger("id ou mot de passe incorrect\r\n1 - reessayer\r\n2 - quitter", Arrays.asList(1,2));

                    }else{
                        identified = true;
                        System.out.println("identifié en tant que "+e.getPrenom()+" "+e.getNom());
                    }
                }
                while(true){
                    choix=Saisie.lireInteger("1 - onglet voyance\r\n2 - onglet stats\r\n3 - deconnexion", Arrays.asList(1,2,3));
                    switch(choix){
                        case 1 :
                            while(true){
                                System.out.println("Onglet voyance");
                                if(e.getStatus()==true){
                                    System.out.println("Pas de voyances attribuée");
                                    break;
                                }else{
                                    Voyance v = Service.getVoyanceEnCours(e);
                                    System.out.println("Voyance en cours : ");
                                    System.out.print("    Status : "+ v.getStatus());
                                    if(v.getStatus()!=Voyance.Status.EnAttente){
                                        System.out.println(" (début : "+v.getDebut()+")");
                                    }else{
                                        System.out.println();
                                    }
                                    System.out.println("    Medium : "+ v.getMedium());
                                    System.out.println("    Client : "+ v.getClient().getPrenom()+" "+v.getClient().getNom());
                                    choix = Saisie.lireInteger("1 - voir profil client\r\n2 - voir historique client\r\n3 - générer prédiction\r\n 4 - changer d'onglet", Arrays.asList(1,2,3,4));
                                    switch(choix){
                                        case 1 :
                                            System.out.println(v.getClient());
                                            Saisie.lireInteger("Presser 1 pour retour à l'onglet voyance",Arrays.asList(1));
                                            choix =2;
                                            break;
                                        case 2 :
                                            System.out.println(Service.getHistorique(v.getClient()));
                                            Saisie.lireInteger("Presser 1 pour retour à l'onglet voyance",Arrays.asList(1));
                                            break;
                                        case 3 :
                                            int amour = Saisie.lireInteger("points amour : ");
                                            int sante = Saisie.lireInteger("points sante : ");
                                            int travail = Saisie.lireInteger("points travail : ");
                                            List<String> predictions = Service.genererPredictions(v.getClient(), amour, sante, travail);
                                            System.out.println(predictions.get(0)+"\r\n"+predictions.get(1)+"\r\n"+predictions.get(2));
                                            Saisie.lireInteger("Presser 1 pour retour à l'onglet voyance",Arrays.asList(1));
                                            choix=2;
                                            break;
                                        case 4 : 
                                            break;
                                    }
                                    if(choix==4){
                                        break;
                                    }
                                            
                                    
                                }
                            }      
                            break;                            
                        case 2 :
                            System.out.println("Onglet stats");
                            Pair<HashMap<Medium,Long>,HashMap<Employe,Long>> stats;
                            stats = Service.getStats();
                            System.out.println("    Statistiques Mediums :");System.out.println();
                            System.out.println();
                            System.out.println(stats.getKey());
                            System.out.println();
                            System.out.println(stats.getValue());
                            Saisie.lireInteger("Presser 1 pour retour au choix des onglets",Arrays.asList(1));                            
                            break;
                        case 3 :
                            break;
                    }
                    if (choix==3){
                        choix = 2;
                        break; 
                    }
                }
            }
        }
    }
}
