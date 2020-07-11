/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity;

import de.hsos.kbse.interfaces.AbstractEntity;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author pmarkman
 */
@Entity
@Table(name = "PERSONAL")
public class Personal extends AbstractEntity {

    // Attribute
    @Column(name = "name")
    private String name;

    @Column(name = "vorname")
    private String vorname;

    @Column(name = "telefon")
    private String telefon;

    @Column(name = "straße")
    private String straße;

    @Column(name = "ort")
    private String ort;

    @Column(name = "plz")
    private Integer plz;

    @Column(name = "durchwahl")
    String durchwahl;

    @Column(name = "bueronr")
    String bueroNr;

    @Embedded
    private Login login = new Login();

    public Personal() {
    }

    public Personal(String name, String vorname, String telefon, String straße, String ort, Integer plz, String durchwahl, String bueroNr) {
        this.name = name;
        this.vorname = vorname;
        this.telefon = telefon;
        this.straße = straße;
        this.ort = ort;
        this.plz = plz;
        this.durchwahl = durchwahl;
        this.bueroNr = bueroNr;
    }

    public Personal(String name, String vorname, String email, String telefon, String ort, String straße, Integer plz, String durchwahl, String bueroNr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getStraße() {
        return straße;
    }

    public void setStraße(String straße) {
        this.straße = straße;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public Integer getPlz() {
        return plz;
    }

    public void setPlz(Integer plz) {
        this.plz = plz;
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

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.vorname);
        hash = 67 * hash + Objects.hashCode(this.telefon);
        hash = 67 * hash + Objects.hashCode(this.straße);
        hash = 67 * hash + Objects.hashCode(this.ort);
        hash = 67 * hash + Objects.hashCode(this.plz);
        hash = 67 * hash + Objects.hashCode(this.durchwahl);
        hash = 67 * hash + Objects.hashCode(this.bueroNr);
        hash = 67 * hash + Objects.hashCode(this.login);
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
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.vorname, other.vorname)) {
            return false;
        }
        if (!Objects.equals(this.telefon, other.telefon)) {
            return false;
        }
        if (!Objects.equals(this.straße, other.straße)) {
            return false;
        }
        if (!Objects.equals(this.ort, other.ort)) {
            return false;
        }
        if (!Objects.equals(this.durchwahl, other.durchwahl)) {
            return false;
        }
        if (!Objects.equals(this.bueroNr, other.bueroNr)) {
            return false;
        }
        if (!Objects.equals(this.plz, other.plz)) {
            return false;
        }
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Personal{" + "name=" + name + ", vorname=" + vorname + ", telefon=" + telefon + ", stra\u00dfe=" + straße + ", ort=" + ort + ", plz=" + plz + ", durchwahl=" + durchwahl + ", bueroNr=" + bueroNr + ", login=" + login + '}';
    }

}
