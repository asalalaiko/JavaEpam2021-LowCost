package by.asalalaiko.servlet.user;

import by.asalalaiko.domain.Flight;
import by.asalalaiko.domain.Ticket;
import by.asalalaiko.domain.TicketStatus;
import by.asalalaiko.domain.User;
import by.asalalaiko.service.FlightService;
import by.asalalaiko.service.TicketService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;


@WebServlet(value = "/user/pay", name = "payTickets")
public class UserPayServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long flightId = Long.valueOf(req.getParameter("flight"));
        Long quantity = Long.valueOf(req.getParameter("quantity"));
        boolean baggage = req.getParameter("baggage")!= null;
        boolean priority = req.getParameter("priority")!= null;
        TicketStatus status = TicketStatus.FREE;
        Flight flight = FlightService.findById(flightId);
        BigDecimal sum = BigDecimal.ZERO;
        User user = (User) req.getSession().getAttribute("user");


        try {

            List<Ticket> freeTickets = (List<Ticket>) TicketService.findByFlightIdAndStatus(flightId, status);

            sum = sum.add(flight.getCost().multiply(new BigDecimal(quantity)));
            if(baggage) sum = sum.add(flight.getCostBaggage().multiply(new BigDecimal(quantity)));
            if(baggage) sum = sum.add(flight.getCostPriority().multiply(new BigDecimal(quantity)));

            if (freeTickets.size() >= quantity) {
                freeTickets.stream().limit(quantity).forEach(t -> {
                    TicketService.getInstance().update(t.getId(),"", baggage, priority, flight, user, TicketStatus.PAID);
                });
            }


            req.setAttribute("flight", flight);
            req.setAttribute("quantity", quantity);
            req.setAttribute("sum", sum);
            req.getRequestDispatcher("/user/pay.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

}
