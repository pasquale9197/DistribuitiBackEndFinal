package com.distribuitibackend.Entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user",  schema = "public")
public class User implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JoinColumn(name = "Id")
    private long id;

    @Basic
    @Column(name = "superuser")
    private boolean superuser;

    @Basic
    @Column(name = "nome")
    private String nome;

    @Basic
    @Column(name = "cognome")
    private String cognome;

    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "password")
    private double password;

    @Basic
    @Column(name = "nomeutente")
    private String nomeutente;

    public User()
    {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(nome, user.nome) && Objects.equals(cognome, user.cognome)
                && Objects.equals(email, user.email) && Objects.equals(password, user.password) &&
                Objects.equals(nomeutente, user.nomeutente) && Objects.equals(superuser, user.superuser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cognome, email, password, nomeutente);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getPassword() {
        return password;
    }

    public void setPassword(double password) {
        this.password = password;
    }

    public String getNomeutente() {
        return nomeutente;
    }

    public void setNomeutente(String nomeutente) {
        this.nomeutente = nomeutente;
    }

    public void setSuperuser(boolean superuser)
    {   this.superuser = superuser;
    }

    public boolean getSuperuser()
    {   return this.superuser;
    }

    public User(String nome, String cognome, String email, int password, String nomeutente, boolean superuser) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.nomeutente = nomeutente;
        this.superuser = superuser;
    }
}

