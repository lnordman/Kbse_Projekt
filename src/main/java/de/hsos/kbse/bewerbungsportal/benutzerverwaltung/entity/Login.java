package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity;

import java.io.Serializable;
import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Email;
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
@Embeddable
public class Login implements Serializable {

//    @NotNull(message = "Email cannot be null")
//    @JsonbProperty("Email")
    @Email
    @Column(name = "email")
    private String email;

//    @NotNull(message = "Password cannot be null")
//    @Size(min = 2, max = 30, message = "Password must between 2 and 30 characters")
    @JsonbProperty("Password")
    @Column(name = "password")
    private String password;

    public Login() {
    }

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
