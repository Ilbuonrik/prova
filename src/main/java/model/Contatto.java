package model;
//id_utente???
public class Contatto {
    private int idutente,idcontatto;
    private String nome, cognome, numero;

    public Contatto(){}
    public String getNome() {
        return nome;
    }
    public String getCognome() {
        return cognome;
    }
    public String getNumero() {
        return numero;
    }

    public int getIdutente() {
        return idutente;
    }

    public int getIdcontatto() {
        return idcontatto;
    }

    public void setIdutente(int idutente) {
        this.idutente = idutente;
    }

    public void setIdcontatto(int idcontatto) {
        this.idcontatto = idcontatto;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "idcontatti="  +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", numero='" + numero + '\'' +
                '}';
    }
}
