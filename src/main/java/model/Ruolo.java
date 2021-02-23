package model;

public class Ruolo {
    private int idruolo;
    private String descrizione;

   public Ruolo(){}

    public int getIdruolo() {
        return idruolo;
    }
    public String getDescrizione() {
        return descrizione;
    }

    public void setIdruolo(int idruolo) {
        this.idruolo = idruolo;
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public String toString() {
        return "Role{" +
                "idruolo=" + idruolo +
                ", descrizione='" + descrizione + '\'' +
                '}';
    }
}
