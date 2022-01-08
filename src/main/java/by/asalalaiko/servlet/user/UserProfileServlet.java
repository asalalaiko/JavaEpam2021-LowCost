package by.asalalaiko.servlet.user;

import by.asalalaiko.domain.User;
import by.asalalaiko.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(value = "/user/profile", name = "profileUser")
public class UserProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {


            req.getRequestDispatcher("/user/profile.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new IOException(e);
        }
    }
}
