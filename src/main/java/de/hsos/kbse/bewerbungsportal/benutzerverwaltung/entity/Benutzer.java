package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity;

import de.hsos.kbse.interfaces.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
@MappedSuperclass
public class Benutzer extends AbstractEntity {

    @NotNull
    @Valid // Gute Erklärung nötig
//    @JsonbProperty("person-name")
    @Size(min = 2, max = 30)
    @Column(name = "name")
    private String name;

    @NotNull
    @Size(min = 2, max = 20)
    @Column(name = "vorname")
    private String vorname;

    @NotNull
    @Column(name = "telefon")
    private String telefon;

    @NotNull
    @Size(min = 2, max = 30)
    @Column(name = "straße")
    private String straße;

    @NotNull
    @Size(min = 2, max = 30)
    @Column(name = "ort")
    private String ort;

    @NotNull
    @Column(name = "plz")
    private Integer plz;

    @Embedded
    Login login = new Login();

    public Benutzer() {
    }

    public Benutzer(String name, String vorname) {
        this.name = name;
        this.vorname = vorname;
    }

    public Benutzer(String name, String vorname, String telefon, String straße, String ort, Integer plz, Login login) {
        this.name = name;
        this.vorname = vorname;
        this.telefon = telefon;
        this.straße = straße;
        this.ort = ort;
        this.plz = plz;
        this.login = login;
    }

}
