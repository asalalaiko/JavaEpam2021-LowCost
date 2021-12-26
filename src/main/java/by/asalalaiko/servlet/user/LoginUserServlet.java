package by.asalalaiko.servlet.user;

import by.asalalaiko.domain.User;
import by.asalalaiko.repo.UserRepo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("pass");

        try {
            if (login != null && password != null) {
                UserRepo repo = UserRepo.getInstance();

                User user = repo.findByLogin(login);

                if (user != null && user.getPassword().equals(password)) {
                    HttpSession session = req.getSession();
                    session.setAttribute("login", login);
                    session.setAttribute("firstName", user.getFirstName());
                    session.setAttribute("lastName", user.getLastName());

                    req.getRequestDispatcher("/").forward(req, resp);
                } else
                {
                    req.setAttribute("message", "Error Login or Password");
                    req.getRequestDispatcher("/login.jsp").forward(req, resp);
                }
            } else {

                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }

        } catch (Exception e) {
            throw new IOException(e);
        }




    }
}
