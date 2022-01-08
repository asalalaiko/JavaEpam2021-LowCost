package by.asalalaiko.servlet.admin.flight;

import by.asalalaiko.domain.Airport;
import by.asalalaiko.domain.City;
import by.asalalaiko.domain.Flight;
import by.asalalaiko.domain.Plane;
import by.asalalaiko.service.AirportService;
import by.asalalaiko.service.CityService;
import by.asalalaiko.service.FlightService;
import by.asalalaiko.service.PlaneService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(value = "/admin/flight/edit", name = "flightEditAdmin")
public class FlightEditServlet  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");

        Flight flight = FlightService.getInstance().getById(Long.valueOf(id));

        req.setAttribute("flight", flight);

        Collection<Flight> flights = FlightService.findAll();
        req.setAttribute("flights", flights);

        Collection<Airport> airports = AirportService.findAll();
        req.setAttribute("airports", airports);

        Collection<Plane> planes = PlaneService.findAll();
        req.setAttribute("planes", planes);



        req.getRequestDispatcher("/admin/flight.jsp").forward(req, resp);

    }
}
