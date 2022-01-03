package by.asalalaiko.servlet;

import by.asalalaiko.domain.User;
import by.asalalaiko.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/login", name = "login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user = UserService.getInstance().login(login, password);

        req.getSession().setAttribute("user", user);

        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}