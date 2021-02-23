package repository;

import model.Contatto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ContactsRepository {
    public List<Contatto> getRubrica();
    public void save(Contatto contatto) throws IOException;
    public void modify(Contatto contatto) throws IOException;
    public void delete(int id) throws IOException, SQLException;
}
