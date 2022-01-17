package by.asalalaiko.servlet.admin.flight;

import by.asalalaiko.domain.Ticket;
import by.asalalaiko.domain.TicketStatus;
import by.asalalaiko.service.FlightService;
import by.asalalaiko.service.TicketService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(value = "/admin/flight/del", name = "flightDeleteAdmin")
public class FlightDelServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameter = req.getParameter("id");

        Long flightId = Long.valueOf(parameter);
        try {

            List<Ticket> tickets = (List<Ticket>) TicketService.findByFlightId(flightId);
            if (tickets.size() > 0) {
                tickets.stream().forEach(t -> {
                    TicketService.getInstance().update(t.getId(),"", t.getBaggage(), t.getPriority(), t.getFlight(), t.getUser(), TicketStatus.CLOSED);
                });
            }

            FlightService.deleteById(flightId);

            req.getRequestDispatcher("/admin/flight").forward(req, resp);

        } catch (Exception e) {
            throw new IOException(e);
        }

    }
}
