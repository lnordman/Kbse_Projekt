package de.hsos.kbse.bewerbungsportal.stellenverwaltung.entity;

import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Personal;
import de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.entity.Bewerbung;
import de.hsos.kbse.interfaces.AbstractEntity_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-05T18:32:45")
@StaticMetamodel(Stelle.class)
public class Stelle_ extends AbstractEntity_ {

    public static volatile SingularAttribute<Stelle, String> ort;
    public static volatile SetAttribute<Stelle, Bewerbung> bewerbung;
    public static volatile SingularAttribute<Stelle, String> bezeichnung;
    public static volatile SingularAttribute<Stelle, Personal> personal;
    public static volatile SingularAttribute<Stelle, String> beschreibung;

}