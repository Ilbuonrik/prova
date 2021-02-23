package repository;

import model.Contatto;
import model.Ruolo;
import model.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface UserRepository {
    public List<User> getUsers();
    public void save(User user) throws IOException;
    public User login(String email, String password) throws IOException;
    public List<Ruolo> getRuoloByUser(int id) throws SQLException;
    public List<Contatto> getContattoByUser(int id) throws SQLException;
    public void deleteUser(int id) throws  SQLException;
}
