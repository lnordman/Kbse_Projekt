/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Personal;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Bewerber;
import de.hsos.kbse.bewerbungsportal.stellenverwaltung.entity.Stelle;
import de.hsos.kbse.interfaces.AbstractEntity;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.validation.Valid;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
/**
 *
 * @author pmarkman
 */
@Entity
@Table(name = "bewerbung")
public class Bewerbung extends AbstractEntity {

//    @Column(name = "zeitstempel")
//    @Valid
    @Temporal(javax.persistence.TemporalType.DATE)
    Date zeitstempel;

    @Column(name = "status")
    @Valid
    String status;

        //______________Personal__________________
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="personal_id")
    @JsonBackReference
    private Personal personal;
    
//        _____________Stelle__________________ 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="stelle_id")
    @JsonBackReference
    private Stelle stelle;
    
    
//    ______________Bewerber__________________
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="bewerber_id")
    @JsonBackReference
    private Bewerber bewerber;



   public Bewerbung() {
    }

    public Bewerbung(Personal personal, Stelle stelle, Bewerber bewerber) {
        this.personal = personal;
        this.stelle = stelle;
        this.bewerber = bewerber;
    }

    public Bewerbung(Date zeitstempel, String status, Personal personal, Stelle stelle, Bewerber bewerber) {
        this.zeitstempel = zeitstempel;
        this.status = status;
        this.personal = personal;
        this.stelle = stelle;
        this.bewerber = bewerber;
    }





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
        return stelle;
    }

    public void setStellen(Stelle stellen) {
        this.stelle = stellen;
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
