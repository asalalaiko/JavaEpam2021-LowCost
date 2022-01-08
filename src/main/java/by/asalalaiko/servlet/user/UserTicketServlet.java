package by.asalalaiko.servlet.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/user/tickets", name = "ticketListUser")
public class UserTicketServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {


            req.getRequestDispatcher("/user/tickets.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new IOException(e);
        }
    }
}
