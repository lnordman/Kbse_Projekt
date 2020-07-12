/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.entity.Bewerbung;
import de.hsos.kbse.bewerbungsportal.stellenverwaltung.entity.Stelle;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Table;

/**
 *
 * @author pmarkman
 */
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
@Table(name="personal")
public class Personal extends Benutzer {

    //Zusätzliche Attribute
    @Column(name = "durchwahl")
//    @NotNull
    @Valid
    String durchwahl;

    @Column(name = "bueronr")
    @NotNull
    String bueroNr;

   
    @OneToMany(fetch = FetchType.LAZY,
            mappedBy="personal",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonManagedReference
    private List<Stelle> stelle;

     @OneToMany(fetch = FetchType.LAZY,
            mappedBy="personal",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonManagedReference
    private List<Bewerbung> bewerbungen;

    public Personal() {
    }



    public List<Bewerbung> getBewerbung() {
        return bewerbungen;
    }


    
   public Personal( String name, String vorname, String email, String telefon, String ort, String straße, Integer plz, String durchwahl, String bueroNr) {
        super(name,vorname,email, telefon, straße, ort, plz);
        this.durchwahl = durchwahl;
        this.bueroNr = bueroNr;
        this.stelle = new ArrayList<>();
    }
    
    
    public void setBewerbung(List<Bewerbung> bewerbung) {
        this.bewerbungen = bewerbung;
    }

    public List<Stelle> getStelle() {
        return stelle;
    }

    public void setStelle(List<Stelle> stelle) {
        this.stelle = stelle;
    }

    public String getDurchwahl() {
        return durchwahl;
    }

    public void setDurchwahl(String durchwahl) {
        this.durchwahl = durchwahl;
    }

    public String getBueroNr() {
        return bueroNr;
    }

    public void setBueroNr(String bueroNr) {
        this.bueroNr = bueroNr;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.durchwahl);
        hash = 17 * hash + Objects.hashCode(this.bueroNr);
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
        final Personal other = (Personal) obj;
        if (!Objects.equals(this.durchwahl, other.durchwahl)) {
            return false;
        }
        if (!Objects.equals(this.bueroNr, other.bueroNr)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Personal{" + "durchwahl=" + durchwahl + ", bueroNr=" + bueroNr + '}';
    }

}
