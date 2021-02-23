package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/logout")
public class LogoutController extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        if (request.getParameter("logout") !=null){
            HttpSession session = request.getSession();
            session.removeAttribute("utente");
            session.removeAttribute("ruolo");
            session.invalidate();
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }
    }
}
