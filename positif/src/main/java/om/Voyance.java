/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package om;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    @Temporal(DATE)
    private Date debut;
    @Temporal(DATE)
    private Date fin;
    private String com;
    private Status status;

    public Voyance() {
        this.status = Status.EnAttente;
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

    public void setDebut(String date) throws ParseException{
        this.debut = sdf.parse(date);
                
    }
    
    public Date getFin() {
        return fin;
    }

    public void setFin(String date) throws ParseException {
        this.fin=sdf.parse(date);
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
        return "OM.Voyance[ id=" + id + " ]";
    }
    
}
