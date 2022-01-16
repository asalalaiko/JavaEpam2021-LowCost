package by.asalalaiko.servlet.user;

import by.asalalaiko.domain.Flight;
import by.asalalaiko.domain.TicketStatus;
import by.asalalaiko.service.FlightService;
import by.asalalaiko.service.TicketService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;

@WebServlet(value = "/user/order", name = "orderTicket")
public class UserOrderTicketServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long flightId = Long.valueOf(req.getParameter("id"));
        Long quantity = Long.valueOf(req.getParameter("quantity"));
        TicketStatus status = TicketStatus.FREE;

        try {

            Integer qFreeTickets = TicketService.findByFlightIdAndStatus(flightId, status).size();
            String message;

            if (qFreeTickets >= quantity){

                message = "Free tickets - " + qFreeTickets;

                Flight flight = FlightService.findById(flightId);


                req.setAttribute("message", message);
                req.setAttribute("quantity", quantity);
                req.setAttribute("flight", flight);
                HashSet<Long> flights  =(HashSet<Long>) req.getSession().getAttribute("cartFlights");
                if( flights != null) {
                    flights.removeIf(k -> k.equals(flightId));
                    req.getSession().setAttribute("cartFlights", flights);
                }
            }
            else {
                message = "Sorry, but you don't have enough tickets";
                req.setAttribute("message", message);
            }

            req.getRequestDispatcher("/user/order.jsp").forward(req, resp);


        } catch (Exception e) {
            throw new IOException(e);
        }
    }


}
