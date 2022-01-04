package by.asalalaiko.servlet.admin.airport;


import by.asalalaiko.domain.Airport;
import by.asalalaiko.domain.City;
import by.asalalaiko.service.AirportService;
import by.asalalaiko.service.CityService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(value = "/admin/airport/edit", name = "airportEditAdmin")
public class AirportEditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");

        Airport airport = AirportService.getInstance().getById(Long.valueOf(id));

        req.setAttribute("airport", airport);

        Collection<Airport> airports = AirportService.findAll();
        req.setAttribute("airports", airports);

        Collection<City> cities = CityService.findAll();
        req.setAttribute("cities", cities);


        req.getRequestDispatcher("/admin/airport.jsp").forward(req, resp);

    }
}
