package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity;

import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Login;
import de.hsos.kbse.interfaces.AbstractEntity_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< OURS
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-12T14:33:04")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-09T13:12:16")
>>>>>>> THEIRS
@StaticMetamodel(Personal.class)
public class Personal_ extends AbstractEntity_ {

    public static volatile SingularAttribute<Personal, String> ort;
    public static volatile SingularAttribute<Personal, String> straße;
    public static volatile SingularAttribute<Personal, String> telefon;
    public static volatile SingularAttribute<Personal, String> vorname;
    public static volatile SingularAttribute<Personal, String> name;
    public static volatile SingularAttribute<Personal, String> durchwahl;
    public static volatile SingularAttribute<Personal, Login> login;
    public static volatile SingularAttribute<Personal, String> bueroNr;
    public static volatile SingularAttribute<Personal, Integer> plz;

}