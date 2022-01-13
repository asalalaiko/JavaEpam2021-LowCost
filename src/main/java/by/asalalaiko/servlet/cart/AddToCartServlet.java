package by.asalalaiko.servlet.cart;

import by.asalalaiko.domain.Flight;
import by.asalalaiko.service.FlightService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;


@WebServlet(value = "/addcart", name = "addToCart")
public class AddToCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String referer = req.getHeader("Referer");
        HashSet<Long> cartFlights =  new HashSet <Long>();
        HashSet<Long> session  =(HashSet<Long>) req.getSession().getAttribute("cartFlights");
        if( session != null) {cartFlights.addAll(session);}

        Long flightId = Long.valueOf(req.getParameter("id"));

        try {
        Flight flight = FlightService.findById(flightId);
        cartFlights.add(flightId);
        req.getSession().setAttribute("cartFlights", cartFlights);

        resp.sendRedirect(referer);
        } catch (Exception e) {
            throw new IOException(e);
        }

    }
}
