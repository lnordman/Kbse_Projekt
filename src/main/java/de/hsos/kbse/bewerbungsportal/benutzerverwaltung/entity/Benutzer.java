/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity;

import de.hsos.kbse.interfaces.AbstractEntity;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author pmarkman
 */

//@Transactional(Transactional.TxType.MANDATORY) 
//@Vetoed
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
//@Table(name="Benutzer")
@XmlRootElement
public class Benutzer extends AbstractEntity {

    @Column(name="name")
    @NotNull()
    @Valid // Gute Erklärung nötig
    private String name;
    
    @Column(name="vorname")
    @NotNull()
    @Valid
    private String vorname;
    
    @Column(name="email")
    @NotNull()
    @Valid
    private String email;
    
    @Column(name="telefon")
    @NotNull()
    @Valid
    private String telefon;
    
    @Column(name="straße")
    @NotNull()
    @Valid
    private String straße;
    
    @Column(name="ort")
    @NotNull()
    @Valid
    private String ort;
    
    @Column(name="plz")
    @NotNull()
    @Valid
    private Integer plz;
    
    /*@Embedded
    @Valid
    private Login login = new Login();*/

    public Benutzer(String name, String vorname, String email, String telefon, String straße, String ort, Integer plz) {
        this.name = name;
        this.vorname = vorname;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        hash = 73 * hash + Objects.hashCode(this.email);
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
        if (!Objects.equals(this.email, other.email)) {
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
        return "Benutzer{" + "name=" + name + ", vorname=" + vorname + ", email=" + email + ", telefon=" + telefon + ", stra\u00dfe=" + straße + ", ort=" + ort + ", plz=" + plz + '}';
    }
    
}
