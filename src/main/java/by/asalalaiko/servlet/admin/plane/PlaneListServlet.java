package by.asalalaiko.servlet.admin.plane;

import by.asalalaiko.domain.City;
import by.asalalaiko.domain.Plane;
import by.asalalaiko.service.CityService;
import by.asalalaiko.service.PlaneService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(value = "/admin/plane", name = "planeListAdmin")
public class PlaneListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            Collection<Plane> planes = PlaneService.findAll();

            req.setAttribute("planes", planes);

            req.getRequestDispatcher("/admin/plane.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new IOException(e);
        }
    }

}
