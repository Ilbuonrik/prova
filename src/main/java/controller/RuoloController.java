package controller;

import model.Ruolo;
import repository.RuoloRepository;
import repository.RuoloRepositoryImpl;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


    @WebServlet(urlPatterns = "/ruoloController")
    public class RuoloController extends HttpServlet {

        private RuoloRepository ruoloRepository;
        private String dispatch;

        @Override
        public void init() throws ServletException {
            this.ruoloRepository = new RuoloRepositoryImpl();
        }

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            doPost(req, resp);
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            String action = req.getParameter("action");

            switch (action) {
                case "roles": {
                    List<Ruolo> ruoli = ruoloRepository.getRoles();

                    for (Ruolo ruolo : ruoli) {
                        System.out.println(ruolo);
                    }

                    dispatch = "/index.jsp";
                    break;
                }

                case "insertNew": {
                    String descrizione = req.getParameter("descrizione");

                    System.out.println(descrizione);

                    Ruolo ruolo = new Ruolo();
                    ruolo.setDescrizione(descrizione);


                    ruoloRepository.saveRoles(ruolo);
                    dispatch = "/index.jsp"; //contatti rubrica

                    break;
                }

                case "delete":{
                    String id = req.getParameter("Id");
                    ruoloRepository.deleteRoles(Integer.parseInt(id));
                    dispatch = "/index.jsp";

                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(dispatch);
                    dispatcher.forward(req, resp);

                    break;
                }

                case "update":{
                    int id = Integer.parseInt(req.getParameter("id"));
                    String descrizione = req.getParameter("descrizione");

                    Ruolo ruolo = new Ruolo();

                    ruoloRepository.updateRoles(ruolo);
                    dispatch = "/view/update_user.jsp"; //contatti rubrica

                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(dispatch);
                    dispatcher.forward(req, resp);

                    break;
                }
            }
        }
    }

