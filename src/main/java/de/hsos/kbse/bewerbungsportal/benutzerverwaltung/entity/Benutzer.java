package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity;

import de.hsos.kbse.interfaces.AbstractEntity;
import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
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

//    @NotNull(message = "Name cannot be null")
//    @Size(min = 2, max = 30, message = "Nachname must between 2 and 30 characters")
    @JsonbProperty("Nachname")
    @Column(name = "name")
    private String name;

//    @NotNull(message = "Vorname cannot be null")
//    @Size(min = 2, max = 30, message = "Vorname must between 2 and 30 characters")
    @JsonbProperty("Vorname")
    @Column(name = "vorname")
    private String vorname;

//    @NotNull(message = "Telefon cannot be null")
//    @Size(min = 2, max = 30, message = "Telefon must between 2 and 30 characters")
    @JsonbProperty("Telefon")
    @Column(name = "telefon")
    private String telefon;

//    @NotNull(message = "Straße cannot be null")
//    @Size(min = 2, max = 30, message = "Straße must between 2 and 30 characters")
    @JsonbProperty("Straße")
    @Column(name = "straße")
    private String straße;

//    @NotNull(message = "Ort cannot be null")
//    @Size(min = 2, max = 30, message = "Ort must between 2 and 30 characters")
    @JsonbProperty("Ort")
    @Column(name = "ort")
    private String ort;

//    @NotNull(message = "PLZ cannot be null")
    @JsonbProperty("PLZ")
    @Column(name = "plz")
    private Integer plz;

//    @NotNull(message = "Login cannot be null")
    @JsonbProperty("Login")
    @Column(name = "login")
    @Embedded
    Login login = new Login();

    public Benutzer() {
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
