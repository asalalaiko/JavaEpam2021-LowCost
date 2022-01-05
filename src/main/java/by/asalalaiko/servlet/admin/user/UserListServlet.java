package by.asalalaiko.servlet.admin.user;

import by.asalalaiko.domain.Plane;
import by.asalalaiko.domain.User;
import by.asalalaiko.service.PlaneService;
import by.asalalaiko.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(value = "/admin/user", name = "userListAdmin")
public class UserListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            Collection<User> users = UserService.findAll();

            req.setAttribute("users", users);

            req.getRequestDispatcher("/admin/user.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new IOException(e);
        }
    }

}
