package by.asalalaiko.servlet;

import by.asalalaiko.domain.City;
import by.asalalaiko.domain.Flight;
import by.asalalaiko.service.CityService;
import by.asalalaiko.service.FlightService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(value = "/home", name = "HomeServlet")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Collection<Flight> flights = FlightService.findAll();
            req.setAttribute("flights", flights);

            Collection<City> cities = CityService.findAll();
            req.setAttribute("cities", cities);

            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Collection<Flight> flights = FlightService.findAll();
            req.setAttribute("flights", flights);

            Collection<City> cities = CityService.findAll();
            req.setAttribute("cities", cities);

            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }
}
