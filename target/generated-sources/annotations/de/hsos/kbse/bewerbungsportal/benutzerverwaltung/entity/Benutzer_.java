package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity;

import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Login;
import de.hsos.kbse.interfaces.AbstractEntity_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-24T16:47:33")
@StaticMetamodel(Benutzer.class)
public class Benutzer_ extends AbstractEntity_ {

    public static volatile SingularAttribute<Benutzer, String> ort;
    public static volatile SingularAttribute<Benutzer, String> straße;
    public static volatile SingularAttribute<Benutzer, String> telefon;
    public static volatile SingularAttribute<Benutzer, String> vorname;
    public static volatile SingularAttribute<Benutzer, String> name;
    public static volatile SingularAttribute<Benutzer, Login> login;
    public static volatile SingularAttribute<Benutzer, Integer> plz;

}