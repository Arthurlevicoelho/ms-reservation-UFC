package com.arthurlevi.msuser.models;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.UniqueElements;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "TB_USERS")
public class UserModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true)
    private String email;

    private String password;

    @Column(unique = true)
    private Integer registration;

    public UserModel(){}

    public UserModel(UUID id, String email, String password, Integer registration) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.registration = registration;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRegistration() {
        return registration;
    }

    public void setRegistration(Integer registration) {
        this.registration = registration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserModel userModel)) return false;
        return Objects.equals(getId(), userModel.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
