package by.asalalaiko.servlet.admin.user;


import by.asalalaiko.service.PlaneService;
import by.asalalaiko.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/user/lock", name = "userLockUnlockAdmin")
public class UserLockUnlockServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameter = req.getParameter("id");

        Long id= Long.valueOf(parameter);
        try {

            UserService.lockUnlockById(id);

            req.getRequestDispatcher("/admin/user").forward(req, resp);

        } catch (Exception e) {
            throw new IOException(e);
        }

    }

}
