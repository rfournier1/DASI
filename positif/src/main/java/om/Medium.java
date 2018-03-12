/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package om;

import dao.jpaUtil;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author bdurand
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)

public abstract class Medium implements Serializable {

    public enum Talent{
        Voyant,
        Tarologue,
        Astrologue;
    }
    private static final long
            serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;    
    private String nom;
    private String prenom;
    private Talent talent;
    private String bio;
    
    
    
    public Medium(String nom, String prenom, Talent talent, String bio){
       this.nom=nom;
       this.prenom=prenom;
       this.talent=talent;
       this.bio = bio;
    }

    public Medium() {
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
         this.prenom = prenom;
    }        
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medium)) {
            return false;
        }
        Medium other = (Medium) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "om.Medium[ id=" + id + " ]";
    }
    
}
