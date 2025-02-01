package com.jobease.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fName;
    private String lName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String pwdHash;

     @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Preferences preferences;

    protected User() {}

    public User(String email, String pwdHash) {
        this.email = email;
        this.pwdHash = pwdHash;
    }

    public User(String fName, String lName, String email, String pwdHash) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.pwdHash = pwdHash;
    }

    public Long getId() {
        return this.id;
    }

    public String getFName() {
        return this.fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return this.lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwdHash() {
        return this.pwdHash;
    }

    public void setPwdHash(String pwdHash) {
        this.pwdHash = pwdHash;
    }
}
