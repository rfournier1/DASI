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
public class Astrologue extends Medium{
   
    private String ecole;
    private String promo;
    
    public Astrologue(String nom, String prenom, String ecole, String promo, String bio){
        super(nom,prenom,Medium.Talent.Astrologue, bio);
        this.promo=promo;   
        this.ecole=ecole;
    }

    public Astrologue() {
    }
    
}
