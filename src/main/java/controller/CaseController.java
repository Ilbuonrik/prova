package controller;

import model.Contatto;
import model.User;
import repository.UserRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/caseController")
public class CaseController extends HttpServlet {
    private String dispatch;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String action = req.getParameter("action");

        switch (action) {
            case "add": {
                User user = (User) session.getAttribute("user");
                if (session != null && session.getAttribute("user") != null) {
                    dispatch = "/view/addcontatto.jsp";
                } else {
                    dispatch = "/index.jsp";
                }
                break;
            }
            case "modify": {
                User user = (User) session.getAttribute("user");
                String idcontatto = req.getParameter("idcontatto");

                if (session != null && session.getAttribute("user") != null) {
                    for (Contatto contatto : user.getRubrica()) {
                        int ID = Integer.parseInt(idcontatto);
                        if (contatto.getIdcontatto() == ID) {
                            session.setAttribute("contatto", contatto);
                            break;
                        }
                    }
                    dispatch = "/view/updatecontatto.jsp";
                } else {
                    dispatch = "/index.jsp";
                }
                break;
            }
            case "backToHome":{
                if (session != null && session.getAttribute("user") !=null){
                    User user = (User) session.getAttribute("user");

                    if (user != null){
                        session.setAttribute("user", user);
                        dispatch = "/view/home.jsp";
                    } else{
                        dispatch = "/index.jsp";
                    }
                    break;
                }
            }

        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(dispatch);
        dispatcher.forward(req, resp);
    }
}
