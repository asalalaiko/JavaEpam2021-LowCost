package by.asalalaiko.servlet.cart;

import by.asalalaiko.domain.Flight;
import by.asalalaiko.service.FlightService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;


@WebServlet(value = "/cart", name = "CartServlet")
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {


            Collection<Flight> flights = new ArrayList<Flight>();
            HashSet<Long> session  =(HashSet<Long>) req.getSession().getAttribute("cartFlights");
            if( session != null) {
                    session.stream().forEach((k) -> {flights.add(FlightService.findById(k));});
                }
            req.setAttribute("flights", flights);
            req.getRequestDispatcher("/cart.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }
}
