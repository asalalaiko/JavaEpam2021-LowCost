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
import java.math.BigDecimal;

@WebServlet(value = "/admin/airport/save", name = "airportSaveAdmin")
public class AirportSaveServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String taxString = req.getParameter("tax");
        String cityId = req.getParameter("city");
        BigDecimal tax = BigDecimal.valueOf(Double.valueOf(taxString));
        City city = CityService.getInstance().getById(Long.valueOf(cityId));



        if (id == "") {
            Airport airport = AirportService.getInstance().create(name, tax, city);
        //    req.setAttribute("createdAirport", airport);
        } else {
            Airport airport = AirportService.getInstance().update(Long.valueOf(id), name, tax, city);
        //    req.setAttribute("updatedAirport", airport);
        }

        req.getRequestDispatcher("/admin/airport").forward(req, resp);
    }
}
