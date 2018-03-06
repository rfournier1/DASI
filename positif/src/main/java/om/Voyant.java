/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package om;

import javax.persistence.Entity;

/**
 *
 * @author bdurand
 */
@Entity
public class Voyant extends Medium{
   
    public enum Support{
        BouleDeCristal("Boule de Cristal"),
        MarcDeCafe("Marc de Café");
        
        private String nom;
        
        Support(String nom){
             this.nom = nom;
        }
        
        @Override
        public String toString(){
            return nom;
        }
    }
    Support support;
    
    public Voyant(String nom, String prenom, Support support,String bio){
        super(nom,prenom,Talent.Voyant,bio);
        this.support=support;        
    }

    public Voyant() {
    }
    
}
