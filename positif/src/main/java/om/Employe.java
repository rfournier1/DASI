/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package om;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * 
 * @author bdurand 
 */
@Entity
public class Employe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String prenom;
    @Column(unique=true)
    private String identifiant;
    private String mdp;
    private boolean status;
    @OneToMany
    private Collection<Medium> mediumsPossibles;

    public Employe(String nom, String prenom, String identifiant, String mdp, Collection<Medium> mediums) {
        this.nom = nom;
        this.prenom = prenom;
        this.identifiant = identifiant;
        this.mdp = mdp;
        this.mediumsPossibles=mediums;
        status = true;
    }

    public Employe() {
        nom="Doe";
        prenom="John";
        identifiant="JDoe";
        mdp="azerty";
        status=true;
    }
    public String getPrenom(){
        return prenom;
    }
    
    public String getNom(){
        return nom;
    }
    
    public String getIdentifiant() {
        return identifiant;
    }

    public String getMdp() {
        return mdp;
    }
    
    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    public Collection<Medium> getMediumsPossibles() {
        return mediumsPossibles;
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
        if (!(object instanceof Employe)) {
            return false;
        }
        Employe other = (Employe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Employe{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", identifiant=" + identifiant + ", mdp=" + mdp + ", status=" + status + '}';
    }
    
    
}
