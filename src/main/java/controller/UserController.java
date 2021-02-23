package controller;

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
import java.io.*;
import java.util.List;

@WebServlet("/userController")
public class UserController extends HttpServlet {

    private UserRepository userRepository;
    private ContactsRepository contactsRepository;
    private String dispatch;

    @Override
    public void init() throws ServletException {
        try {
            this.userRepository = new UserRepositoryImpl();
            this.contactsRepository = new ContactsRepositoryImpl();

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


        switch(action) {
            case "users": {
                List<User> users = userRepository.getUsers();

                HttpSession session = req.getSession();
                if(session!= null && session.getAttribute("user") !=null) {
                    session.setAttribute("users", users);
                    dispatch = "/view/homepage_admin.jsp";
                }else {
                    dispatch = "/index.jsp";
                }
                break;
            }

            case "register": {
                String name = req.getParameter("name");
                String surname = req.getParameter("surname");
                String email = req.getParameter("email");
                String password = req.getParameter("password");

                System.out.println(name + " " + surname + " " + email + " " + password);

                User user = new User();
                user.setNome(name);
                user.setCognome(surname);
                user.setEmail(email);
                user.setPassword(password);

                userRepository.save(user);
                dispatch = "/index.jsp";

                break;
            }

            case "login": {
                String email = req.getParameter("email");
                String password = req.getParameter("password");

                HttpSession session = req.getSession();

                User user = userRepository.login(email, password);
                if(user != null) {
                    session.setAttribute("user", user);
                    dispatch = "/view/home.jsp";
                }
                else {
                    dispatch = "/index.jsp";
                }

                break;
            }

            case "logout":{
                HttpSession session = req.getSession();
                if(session!=null && session.getAttribute("user")!=null){
                    session.removeAttribute("user");
                    session.invalidate();
                    dispatch = "/index.jsp";
                }else{
                    dispatch="/view/home.jsp";
                }

                break;
            }

            case "deleteUser":{
                String id = req.getParameter("id");
                int ID = Integer.parseInt(id);

                User user = null;

            }

        }


        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(dispatch);
        dispatcher.forward(req, resp);

    }
}
