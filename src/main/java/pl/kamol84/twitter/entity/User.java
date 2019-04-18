package pl.kamol84.twitter.entity;

import pl.kamol84.twitter.validationGroup.FullValidation;
import pl.kamol84.twitter.validationGroup.PartValidation;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(groups = {Default.class, FullValidation.class})
    private String firstName, lastName;

    @NotBlank(groups = {FullValidation.class, PartValidation.class})
    private String password;

    @NotBlank(groups = {Default.class, FullValidation.class, PartValidation.class})
    @Email(groups = {Default.class, FullValidation.class, PartValidation.class})
    private String email;

    @Transient
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    @Override
    public String toString() {
        return getFullName();
    }
}
