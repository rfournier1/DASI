/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package om;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;
import javax.persistence.Transient;


/**
 *
 * @author bdurand
 */
@Entity
public class Client implements Serializable {
    //public SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    public enum Civilite{
        Mr,
        Mme,
        Autre;
        
        public static Civilite intToCivilite(int i){
            switch(i){
                case 1 :
                    return Mr;
                case 2 :
                    return Mme;
                case 3 :
                    return Autre;
                default :
                    return Mr;
            }
        }
    }
//    
//    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique=true)
    private String identifiant;
    private String mdp;
    private Civilite civilite;
    private String nom;
    private String prenom;
    @Temporal(DATE)
    private Date date;
    @Column(unique=true)
    private String email;
    private String adresse;
    private String tel;
    private String zodiac;
    private String signeChinois;
    private String couleur;
    private String animalTotem;

    public Client(Civilite civilite, String nom, String prenom, Date date, String email, String adresse, String tel, String identifiant, String mdp){
        this.identifiant = identifiant;
        this.mdp = mdp;
        this.civilite = civilite;
        this.nom = nom;
        this.prenom = prenom;
        this.date = date;
        this.email = email;
        this.adresse = adresse;
        this.tel = tel;
    }

    public Client() {
    }

    public Long getId() {
        return id;
    }
    
    public void setProfilAstro(List<String> profil){
        this.zodiac=profil.get(0);
        this.signeChinois=profil.get(1);
        this.couleur=profil.get(2);
        this.animalTotem=profil.get(3);
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

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", identifiant=" + identifiant + ", mdp=" + mdp + ", civilite=" + civilite + ", nom=" + nom + ", prenom=" + prenom + ", date=" + date + ", email=" + email + ", adresse=" + adresse + ", tel=" + tel + ", zodiac=" + zodiac + ", signeChinois=" + signeChinois + ", couleur=" + couleur + ", animalTotem=" + animalTotem + '}';
    }
    
    
}
