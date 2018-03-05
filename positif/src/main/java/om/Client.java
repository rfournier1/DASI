/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package om;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 *
 * @author bdurand
 */
@Entity
public class Client implements Serializable {
    
    public enum Civilite{
        Mr,
        Mme;
    }
//    
//    private static final long serialVersionUID = 1L;
    @Id
    private String identifiant;
    private String mdp;
    private Civilite civilite;
    private String nom;
    private String prenom;
    private Date date;
    private String email;
    private String adresse;
    private String tel;
    private String zodiac;
    private String signeChinois;
    private String couleur;
    private String animalTotem;

    public Client(Civilite civilite, String nom, String prenom, Date date, String email, String adresse, String tel, String identifiant, String mdp) {
        this.identifiant = identifiant;
        this.mdp = mdp;
        this.civilite = civilite;
        this.nom = nom;
        this.prenom = prenom;
        this.date = date;
        this.email = email;
        this.adresse = adresse;
        this.tel = tel;
        this.calculerProfilAstro();
    }

    public Client() {
    }
    
    private void calculerProfilAstro(){
        this.zodiac="Scorpion";
        this.couleur="bleu";
        this.signeChinois="Rat";
        this.animalTotem="panda";
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public String getMdp() {
        return mdp;
    }
    
    public Civilite getCivilite() {
        return civilite;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Date getDate() {
        return date;
    }

    public String getEmail() {
        return email;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getTel() {
        return tel;
    }

    public String getZodiac() {
        return zodiac;
    }

    public String getSigneChinois() {
        return signeChinois;
    }

    public String getCouleur() {
        return couleur;
    }

    public String getAnimalTotem() {
        return animalTotem;
    }

    
}
