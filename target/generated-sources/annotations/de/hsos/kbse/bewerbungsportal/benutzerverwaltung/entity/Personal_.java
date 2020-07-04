package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity;

import de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.entity.Bewerbung;
import de.hsos.kbse.bewerbungsportal.stellenverwaltung.entity.Stelle;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-04T15:51:53")
@StaticMetamodel(Personal.class)
public class Personal_ extends Benutzer_ {

    public static volatile SetAttribute<Personal, Bewerbung> bewerbungen;
    public static volatile SingularAttribute<Personal, String> durchwahl;
    public static volatile SingularAttribute<Personal, String> bueroNr;
    public static volatile SetAttribute<Personal, Stelle> stelle;

}