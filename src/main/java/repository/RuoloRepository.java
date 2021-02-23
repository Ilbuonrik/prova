package repository;

import model.Ruolo;

import java.io.IOException;
import java.util.List;

public interface RuoloRepository {
    public List<Ruolo> getRoles();
    public void saveRoles(Ruolo ruolo) throws IOException;
    public void updateRoles(Ruolo ruolo) throws IOException;
    public void deleteRoles(int id) throws IOException;
    public Ruolo getDefaultRuolo(String descrizione);
}
