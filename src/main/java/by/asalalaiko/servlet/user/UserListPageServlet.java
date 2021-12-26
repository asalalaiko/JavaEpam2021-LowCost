package by.asalalaiko.servlet.user;


import by.asalalaiko.domain.User;
import by.asalalaiko.repo.UserRepo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class UserListPageServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            UserRepo repo = UserRepo.getInstance();

            Collection<User> all = repo.findAll();

            req.setAttribute("users", all);

            req.getRequestDispatcher("users.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new IOException(e);
        }
    }
}
