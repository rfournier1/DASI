/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package om;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;

/**
 *
 * @author bdurand
 */
@Entity
public class Voyance {
    
   public enum Status{
       EnCours,
       EnAttente,
       Termine;
   } 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(DATE)
    private Date debut;
    @Temporal(DATE)
    private Date fin;
    private String com;
    private Status status;
    private Long clientid;
    private Long mediumid;
    private Long employeid;

    public Voyance(Client c, Employe e, Medium m) {
        this.status = Status.EnAttente;
        this.clientid=c.getId();
        this.mediumid=m.getId();
        this.employeid=e.getId();
    }
    public Voyance(){
    }

    public Long getClient() {
        return clientid;
    }

    public Long getMedium() {
        return mediumid;
    }

    public Long getEmploye() {
        return employeid;
    }
    
    
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date date){
        this.debut = date;
                
    }
    
    public Date getFin() {
        return fin;
    }

    public void setFin(Date date){
        this.fin=date;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Voyance{" + "id=" + id + ", debut=" + debut + ", fin=" + fin + ", com=" + com + ", status=" + status + ", client=" + clientid + ", medium=" + mediumid + ", employe=" + employeid + '}';
    }

    
}
