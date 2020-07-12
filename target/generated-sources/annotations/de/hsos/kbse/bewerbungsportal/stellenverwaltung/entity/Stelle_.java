package de.hsos.kbse.bewerbungsportal.stellenverwaltung.entity;

import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Personal;
import de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.entity.Bewerbung;
import de.hsos.kbse.interfaces.AbstractEntity_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< OURS
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-12T14:33:04")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-09T13:12:16")
>>>>>>> THEIRS
@StaticMetamodel(Stelle.class)
public class Stelle_ extends AbstractEntity_ {

    public static volatile SingularAttribute<Stelle, String> ort;
    public static volatile SingularAttribute<Stelle, String> bezeichnung;
    public static volatile SetAttribute<Stelle, Bewerbung> bewerbungen;
    public static volatile SingularAttribute<Stelle, Personal> personal;
    public static volatile SingularAttribute<Stelle, String> beschreibung;

}