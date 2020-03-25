package com.psych.game.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User extends Auditable {
    @Email
    @NotBlank
    @Column(unique = true)
    @Getter
    @Setter
    private String email;

    @NotBlank
    @Getter
    @Setter
    private String saltedHashedPassword;

    @ManyToMany(fetch = FetchType.EAGER)
    @Getter
    @Setter
    Set<Role> roles = new HashSet<>();

    public void setSaltedHashedPassword(String value) {
        this.saltedHashedPassword = new BCryptPasswordEncoder(5).encode(value);
    }

    public User(User user) {
        email = user.email;
        saltedHashedPassword = user.saltedHashedPassword;
        roles = user.getRoles();
    }

    public User(){}
}
