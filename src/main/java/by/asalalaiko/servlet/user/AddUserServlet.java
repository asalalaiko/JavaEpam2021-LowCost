package by.asalalaiko.servlet.user;

import by.asalalaiko.domain.User;
import by.asalalaiko.repo.UserRepo;
import by.asalalaiko.repo.UserRepo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");

        try {
            UserRepo repo = UserRepo.getInstance();

            User user = new User();
            user.setLogin(login);
            user.setFirstName(fname);
            user.setLastName(lname);

            User saved = repo.save(user);

            req.setAttribute("addedId", saved.getId());

            req.getRequestDispatcher("userList").forward(req, resp);

        } catch (Exception e) {
            throw new IOException(e);
        }
    }

}
