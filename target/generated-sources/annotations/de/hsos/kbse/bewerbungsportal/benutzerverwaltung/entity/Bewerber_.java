package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity;

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
@StaticMetamodel(Bewerber.class)
public class Bewerber_ extends AbstractEntity_ {

    public static volatile SingularAttribute<Bewerber, String> ort;
    public static volatile SetAttribute<Bewerber, Bewerbung> bewerbung;
    public static volatile SingularAttribute<Bewerber, String> straße;
    public static volatile SingularAttribute<Bewerber, String> telefon;
    public static volatile SingularAttribute<Bewerber, String> vorname;
    public static volatile SingularAttribute<Bewerber, String> portait_pfad;
    public static volatile SingularAttribute<Bewerber, String> name;
    public static volatile SingularAttribute<Bewerber, String> unterlagen_pfad;
    public static volatile SingularAttribute<Bewerber, String> email;
    public static volatile SingularAttribute<Bewerber, Integer> plz;

}