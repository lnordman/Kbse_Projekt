package de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.entity;

import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Bewerber;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Personal;
import de.hsos.kbse.bewerbungsportal.stellenverwaltung.entity.Stelle;
import de.hsos.kbse.interfaces.AbstractEntity_;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< OURS
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-12T14:33:04")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-09T13:12:16")
>>>>>>> THEIRS
@StaticMetamodel(Bewerbung.class)
public class Bewerbung_ extends AbstractEntity_ {

    public static volatile SingularAttribute<Bewerbung, Bewerber> bewerber;
    public static volatile SingularAttribute<Bewerbung, Date> zeitstempel;
    public static volatile SingularAttribute<Bewerbung, Personal> personal;
    public static volatile SingularAttribute<Bewerbung, Stelle> stellen;
    public static volatile SingularAttribute<Bewerbung, String> status;

}