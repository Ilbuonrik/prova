package model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String nome,cognome,email,password;
    List<Contatto> rubrica = new ArrayList<>();
    List<Ruolo> ruoli = new ArrayList<>();
    private int id;


    public User(){}

    public List<Contatto> getRubrica() {
        return rubrica;
    }

    public void setRubrica(List<Contatto> rubrica) {
        this.rubrica = rubrica;
    }

    public List<Ruolo> getRuoli() {
        return ruoli;
    }

    public void setRuoli(List<Ruolo> ruoli) {
        this.ruoli = ruoli;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", rubrica=" + rubrica +
                ", ruoli=" + ruoli +
                ", id=" + id +
                '}';
    }
}
