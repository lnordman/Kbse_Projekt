package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Email;
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
@Embeddable
public class Login implements Serializable {

    @Email
    @Column(name = "email")
    private String email;

    @Size(min = 2)
    @Column(name = "password")
    private String password;

    public Login() {
    }

    /**
     *
     * @param email
     * @param password
     */
    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
