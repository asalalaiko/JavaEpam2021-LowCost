package by.asalalaiko.servlet.admin.flight;

import by.asalalaiko.service.AirportService;
import by.asalalaiko.service.FlightService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(value = "/admin/flight/del", name = "flightDeleteAdmin")
public class FlightDelServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameter = req.getParameter("id");

        Long flightId = Long.valueOf(parameter);
        try {

            FlightService.deleteById(flightId);

            req.getRequestDispatcher("/admin/flight").forward(req, resp);

        } catch (Exception e) {
            throw new IOException(e);
        }

    }
}
