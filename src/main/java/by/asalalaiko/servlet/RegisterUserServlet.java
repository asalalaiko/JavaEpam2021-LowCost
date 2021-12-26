package by.asalalaiko.servlet;

import by.asalalaiko.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/register", name = "register")
public class RegisterUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");

        req.getSession().setAttribute("user",
                UserService.getInstance().registerUser(login, password, fname, lname, email));

        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }



}
