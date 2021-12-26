package by.asalalaiko.servlet.user;

import by.asalalaiko.repo.UserRepo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String parameter = req.getParameter("userId");

        Long userId = Long.valueOf(parameter);
        try {
            UserRepo repo = UserRepo.getInstance();

            repo.deleteById(userId);

            req.setAttribute("deletedId", userId);

            req.getRequestDispatcher("userList").forward(req, resp);

        } catch (Exception e) {
            throw new IOException(e);
        }

    }
}
