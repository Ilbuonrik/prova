package controller;

import model.Contatto;
import model.User;
import repository.ContactsRepository;
import repository.ContactsRepositoryImpl;
import repository.UserRepository;
import repository.UserRepositoryImpl;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(urlPatterns = "/contattoController")
    public class ContattoController extends HttpServlet {

    private ContactsRepository contactsRepository;
    private UserRepository userRepository;
    private String dispatch;

    @Override
    public void init() throws ServletException {

        try {
            this.contactsRepository = new ContactsRepositoryImpl();
            this.userRepository = new UserRepositoryImpl();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        switch (action) {

            case "insertNew": {
                HttpSession session = req.getSession();
                User user = null;

                if(session != null && session.getAttribute("user") !=null){
                    user = (User) session.getAttribute("user");
                }

                String nome = req.getParameter("nome");
                String cognome = req.getParameter("cognome");
                String numero = req.getParameter("numero");
                int idutente = user.getId();

                System.out.println("Contatto aggiunto: " + nome + " " + cognome + " " + numero + " ,id utente:" + idutente);

                Contatto contatto = new Contatto();
                contatto.setNome(nome);
                contatto.setCognome(cognome);
                contatto.setNumero(numero);
                contatto.setIdutente(idutente);

                contactsRepository.save(contatto);

                try {
                    user.setRubrica(userRepository.getContattoByUser(user.getId()));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                dispatch = "/view/home.jsp"; //contatti rubrica

                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(dispatch);
                dispatcher.forward(req, resp);

                break;
            }

            case "delete":{

                HttpSession session = req.getSession();
                User user = null;

                if(session != null && session.getAttribute("user") !=null){
                    user = (User) session.getAttribute("user");
                }

                String id = req.getParameter("idcontatto");
                try {
                    contactsRepository.delete(Integer.parseInt(id));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    user.setRubrica(userRepository.getContattoByUser(user.getId()));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                dispatch = "/view/home.jsp";

                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(dispatch);
                dispatcher.forward(req, resp);

                break;
            }

            case "update":{

                HttpSession session = req.getSession();
                User user = null;

                if(session != null && session.getAttribute("user") !=null){
                    user = (User) session.getAttribute("user");
                }
                Contatto contatto= (Contatto) session.getAttribute("contatto");

                String nome = req.getParameter("nome");
                String cognome = req.getParameter("cognome");
                String numero = req.getParameter("numero");

                contatto.setNome(nome);
                contatto.setCognome(cognome);
                contatto.setNumero(numero);
                contatto.setIdutente(contatto.getIdutente());
                contatto.setIdcontatto(contatto.getIdcontatto());

                contactsRepository.modify(contatto);
                try {
                    user.setRubrica(userRepository.getContattoByUser(user.getId()));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                dispatch = "/view/home.jsp"; //contatti rubrica

                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(dispatch);
                dispatcher.forward(req, resp);

                break;
            }
        }
    }
}


