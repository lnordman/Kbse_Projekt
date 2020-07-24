package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity;

import de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.entity.Bewerbung;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.codehaus.jackson.annotate.JsonManagedReference;

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
//@Table("bewerber")
public class Bewerber extends Benutzer {

    
    @JsonbProperty(value = "Unterlagen")
    @Column(name = "unterlagen_pfad")
    String unterlagen_pfad;

//    @JsonbProperty(value = "Portrait")
//    @Column(name = "portait_pfad")
//    String portait_pfad;

    //Ein Bewerber kann mehrere Bewerbungen haben
    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "bewerber",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonbProperty("Bewerbung")
        @JsonManagedReference
    private List<Bewerbung> bewerbung;

    public Bewerber() {
    }

    public Bewerber(String name, String vorname, String telefon, String straße, String ort, Integer plz, String unterlagen_pfad,  Login login) {
        super(name, vorname, telefon, straße, ort, plz, login);
        this.unterlagen_pfad = unterlagen_pfad;
 
    }

}
