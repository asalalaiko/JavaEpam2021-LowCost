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
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(value = "/admin/flight/save", name = "flightSaveAdmin")
public class FlightSaveServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        LocalDateTime start = LocalDateTime.parse(req.getParameter("start")+":00.000");
        LocalDateTime finish = LocalDateTime.parse(req.getParameter("finish")+":00.000");
        Integer km = Integer.valueOf(req.getParameter("km"));
        Airport startAirport = AirportService.getInstance().getById(Long.valueOf(req.getParameter("startAirport")));
        Airport finishAirport = AirportService.getInstance().getById(Long.valueOf(req.getParameter("finishAirport")));

        Plane plane = PlaneService.getInstance().getById(Long.valueOf(req.getParameter("plane")));
        BigDecimal cost = BigDecimal.valueOf(Double.valueOf(req.getParameter("cost")));


        if (id == "") {
            Flight flight = FlightService.getInstance().create(start, finish, km, startAirport, finishAirport, plane, cost);
            req.setAttribute("createdFlight", flight);
        } else {
            Flight flight = FlightService.getInstance().update(Long.valueOf(id), start, finish, km, startAirport, finishAirport, plane, cost);
            req.setAttribute("updatedFlight", flight);
        }

        req.getRequestDispatcher("/admin/flight").forward(req, resp);

    }


}
