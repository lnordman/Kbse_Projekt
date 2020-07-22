package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.entity.Bewerbung;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
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
@Table(name = "bewerber")
public class Bewerber extends Benutzer {

    //Ein Bewerber kann mehrere Bewerbungen haben
    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "bewerber",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonManagedReference
    private List<Bewerbung> bewerbung;

    public Bewerber() {
    }

    public Bewerber(String name, String vorname, String telefon, String straße, String ort, Integer plz, String unterlagen_pfad, String portait_pfad, Login login) {
        super(name, vorname, telefon, straße, ort, plz, login);
    }

}
