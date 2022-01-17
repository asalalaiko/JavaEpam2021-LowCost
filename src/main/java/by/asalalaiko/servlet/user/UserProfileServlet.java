package by.asalalaiko.servlet.user;

import by.asalalaiko.domain.Ticket;
import by.asalalaiko.domain.User;
import by.asalalaiko.service.TicketService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/user/profile", name = "profileUser")
public class UserProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");

        try {

            List<Ticket> userTickets = (List<Ticket>) TicketService.findByUser(user);

            req.setAttribute("tickets", userTickets);

            req.getRequestDispatcher("/user/profile.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new IOException(e);
        }
    }
}
