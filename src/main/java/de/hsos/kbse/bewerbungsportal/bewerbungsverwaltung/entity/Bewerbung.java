package de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Personal;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Bewerber;
import de.hsos.kbse.bewerbungsportal.stellenverwaltung.entity.Stelle;
import de.hsos.kbse.interfaces.AbstractEntity;
import java.util.Date;
import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.codehaus.jackson.annotate.JsonBackReference;

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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
    @JsonbProperty("Zeitstempel")
    @Column(name = "zeitstempel")
    @Temporal(javax.persistence.TemporalType.DATE)
    Date zeitstempel;

    @NotNull(message = "Status cannot be null")
    @Size(min = 2, message = "Status must between 2 and 30 characters")
    @JsonbProperty("Status")
    @Column(name = "status")
    String status;

    //______________Personal__________________
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_id")
    @JsonBackReference
    @NotNull(message = "Personal cannot be null")
    @JsonbProperty("Personal")
    private Personal personal;

//        _____________Stelle__________________ 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stelle_id")
    @JsonBackReference
    @NotNull(message = "Stelle cannot be null")
    @JsonbProperty("Stelle")
    private Stelle stelle;

//    ______________Bewerber__________________
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bewerber_id")
    @JsonBackReference
    @NotNull(message = "Bewerber cannot be null")
    @JsonbProperty("Bewerber")
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
