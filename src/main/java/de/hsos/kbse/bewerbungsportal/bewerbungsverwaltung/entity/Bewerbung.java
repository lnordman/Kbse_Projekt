/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.entity;

import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Personal;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Benutzer;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Bewerber;
import de.hsos.kbse.bewerbungsportal.stellenverwaltung.entity.Stelle;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 *
 * @author pmarkman
 */
@Entity
//@Table(name = "bewerbung")
//@NamedQueries ergänzen!
public class Bewerbung extends Benutzer {

    @Column(name = "zeitstempel")
    @Valid
    @Temporal(javax.persistence.TemporalType.DATE)
    Date zeitstempel;

    @Column(name = "status")
    @Valid
    String status;

        //_____________Stelle__________________ 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STELLEN_ID")
    private Stelle stellen;
    
    //______________Bewerber__________________
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BEWERBUNG_ID")
    private Bewerber bewerber;

    //______________Personal__________________
    @ManyToOne
    @JoinColumn(name = "PERSONAL_ID")
    private Personal personal;

   public Bewerbung() {
    }

//    public Bewerbung(String status) {
//        this.status = status;
//    }
//
//    
//
//
//   
//    public Bewerbung(Date zeitstempel, String status) {
//        this.zeitstempel = zeitstempel;
//        this.status = status;
//    }

    public Bewerber getBewerber() {
        return bewerber;
    }

    public void setBewerber(Bewerber bewerber) {
        this.bewerber = bewerber;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public Stelle getStellen() {
        return stellen;
    }

    public void setStellen(Stelle stellen) {
        this.stellen = stellen;
    }

 

    public Date getZeitstempel() {
        return zeitstempel;
    }

    public void setZeitstempel(Date zeitstempel) {
        this.zeitstempel = zeitstempel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.zeitstempel);
        hash = 79 * hash + Objects.hashCode(this.status);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Bewerbung other = (Bewerbung) obj;
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        return Objects.equals(this.zeitstempel, other.zeitstempel);
    }

    @Override
    public String toString() {
        return "Bewerbung{" + "zeitstempel=" + zeitstempel + ", status=" + status + '}';
    }
}
