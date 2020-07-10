package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity;

import de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.entity.Bewerbung;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-09T13:12:16")
@StaticMetamodel(Bewerber.class)
public class Bewerber_ extends Benutzer_ {

    public static volatile SetAttribute<Bewerber, Bewerbung> bewerbung;
    public static volatile SingularAttribute<Bewerber, String> portait_pfad;
    public static volatile SingularAttribute<Bewerber, String> unterlagen_pfad;

}