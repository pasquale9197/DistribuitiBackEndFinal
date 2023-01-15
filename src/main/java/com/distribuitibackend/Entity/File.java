package com.distribuitibackend.Entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "file", schema = "public")
public class File
{
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private User id_user;

    @Basic
    @Column(name = "typefile")
    private String typefile;

    @Basic
    @Column(name = "file")
    private String file;

    @Basic
    @Column(name = "titolo")
    private String titolo;

    @Basic
    @Column(name = "descrizione")
    private String descrizione;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user_preferito")
    private List<User> listaUser;

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public File() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return id == file.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public List<User> getListaUser() {
        return listaUser;
    }

    public void setListaUser(List<User> listaUser) {
        this.listaUser = listaUser;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getId_user() {
        return id_user;
    }

    public void setId_user(User id_user) {
        this.id_user = id_user;
    }

    public String getTypefile() {
        return typefile;
    }

    public void setTypefile(String typefile) {
        this.typefile = typefile;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public File(User id_user, String typefile, String file, String titolo, String descrizione) {
        this.id = id;
        this.id_user = id_user;
        this.typefile = typefile;
        this.file = file;
        this.titolo = titolo;
        this.descrizione = descrizione;
    }

    public File(User id_user, String typefile, String file, String titolo)
    {   this.id = id;
        this.id_user = id_user;
        this.typefile = typefile;
        this.file = file;
        this.titolo = titolo;
    }
}

