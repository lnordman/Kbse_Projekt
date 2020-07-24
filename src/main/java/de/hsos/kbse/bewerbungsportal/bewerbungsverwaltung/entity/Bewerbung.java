package de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Personal;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Bewerber;
import de.hsos.kbse.bewerbungsportal.stellenverwaltung.entity.Stelle;
import de.hsos.kbse.interfaces.AbstractEntity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.validation.Valid;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Philipp Markmann
 * @author Leander Nordmann
 * @version 3
 *
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "bewerbung")
public class Bewerbung extends AbstractEntity {

    @Temporal(javax.persistence.TemporalType.DATE)
    Date zeitstempel;

    @Column(name = "status")
    @Valid
    String status;

    @Column(name = "anlagen_pfad")
    String unterlagen_pfad;

    //______________Personal__________________
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_id")
//    @JsonBackReference
    @JsonIgnore
    private Personal personal;

    //_____________Stelle__________________ 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stelle_id")
//    @JsonBackReference
    @JsonIgnore
    private Stelle stelle;

    //______________Bewerber__________________
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bewerber_id")
//    @JsonBackReference
    @JsonIgnore
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

}
