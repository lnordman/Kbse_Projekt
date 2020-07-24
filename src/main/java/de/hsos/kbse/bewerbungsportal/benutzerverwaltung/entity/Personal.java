package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity;

import de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.entity.Bewerbung;
import de.hsos.kbse.bewerbungsportal.stellenverwaltung.entity.Stelle;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Table;
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
@Table(name = "personal")
public class Personal extends Benutzer {

//    @Column(name = "durchwahl")
    String durchwahl;

//    @Column(name = "bueronr")
//    @NotNull
    String bueroNr;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "personal",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonManagedReference
    private List<Stelle> stelle;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "personal",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonManagedReference
    private List<Bewerbung> bewerbungen;

    public Personal() {
    }
    

    public Personal(String durchwahl, String bueroNr, String name, String vorname, String telefon, String straße, String ort, Integer plz, Login login) {
        super(name, vorname, telefon, straße, ort, plz, login);
        this.durchwahl = durchwahl;
        this.bueroNr = bueroNr;
        this.stelle = new ArrayList<>();
    }

}
