package by.asalalaiko.servlet.admin.airport;

import by.asalalaiko.service.AirportService;
import by.asalalaiko.service.CityService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/airport/del", name = "airportDeleteAdmin")
public class AirportDelServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameter = req.getParameter("id");

        Long airportId = Long.valueOf(parameter);
        try {

            AirportService.deleteById(airportId);

            req.getRequestDispatcher("/admin/airport").forward(req, resp);

        } catch (Exception e) {
            throw new IOException(e);
        }

    }

}
