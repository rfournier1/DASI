/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.Scanner;
import om.Employe;
/**
 *
 * @author romain
 */
public class MainEmploye {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("bienvenue sur positif, service Employe");
        boolean identified = false;
        String id = "";
        String mdp="";
        Employe e=null;
        while(identified == false){
            System.out.println("id ?");
            id = sc.nextLine();
            System.out.println("mdp ?");
            mdp = sc.nextLine();
            e=Service.identificationEmploye(id,mdp);
            if(e==null){
                System.out.println("nom de compte ou mot de passe incorrect, réessayez");
            }else{
                identified = true;
            }
        }
        System.out.println("identifié en tant que"+e.getPrenom()+" "+e.getNom());
            
    }

}
