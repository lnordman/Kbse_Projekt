/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity;

import de.hsos.kbse.interfaces.AbstractEntity;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 *
 * @author pmarkman
 */

//@Entity
@MappedSuperclass
public class Benutzer extends AbstractEntity {

    @Column(name = "name")
    @NotNull()
    @Valid // Gute Erklärung nötig
//    @JsonbProperty("person-name")
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

    public Benutzer(String name, String vorname, String telefon, String straße, String ort, Integer plz) {
        this.name = name;
        this.vorname = vorname;
        this.telefon = telefon;
        this.straße = straße;
        this.ort = ort;
        this.plz = plz;
    }

    public Benutzer() {
    }

    public Benutzer(String name, String vorname) {
        this.name = name;
        this.vorname = vorname;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.name);
        hash = 73 * hash + Objects.hashCode(this.vorname);
        hash = 73 * hash + Objects.hashCode(this.telefon);
        hash = 73 * hash + Objects.hashCode(this.straße);
        hash = 73 * hash + Objects.hashCode(this.ort);
        hash = 73 * hash + Objects.hashCode(this.plz);
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
        final Benutzer other = (Benutzer) obj;
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
        if (!Objects.equals(this.plz, other.plz)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Benutzer{" + "name=" + name + ", vorname=" + vorname + ", telefon=" + telefon + ", stra\u00dfe=" + straße + ", ort=" + ort + ", plz=" + plz + '}';
    }

}
