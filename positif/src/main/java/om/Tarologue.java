/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package om;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author bdurand 
 */
@Entity
@DiscriminatorValue("TAROLOGUE")
public class Tarologue extends Medium{
   
    public enum Cartes{
        Broceliande("Tarot de Brocéliande"),
        Marseille("Tarot de Marseille");
        
        private String nom;
        
        Cartes(String nom){
            this.nom = nom;
        }
        
        @Override
        public String toString(){
            return nom;
        }
    }
    
    Cartes cartes;
    
    public Tarologue(String nom, String prenom, Cartes cartes, String bio){
        super(nom,prenom,Medium.Talent.Tarologue, bio);
        this.cartes=cartes;        
    }

    public Tarologue() {
    }
    
    public String getInfos() {
        return cartes.toString();
    }
}
