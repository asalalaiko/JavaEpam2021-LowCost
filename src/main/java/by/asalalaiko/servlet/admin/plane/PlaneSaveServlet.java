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
import java.math.BigDecimal;

@WebServlet(value = "/admin/plane/save", name = "planeSaveAdmin")
public class PlaneSaveServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String model = req.getParameter("model");
        Integer seats = Integer.valueOf(req.getParameter("seats"));
        BigDecimal costKM = BigDecimal.valueOf(Double.valueOf(req.getParameter("costKM")));

        if (id == "") {
            Plane plane = PlaneService.getInstance().create(name, model, seats, costKM);
            req.setAttribute("createdPlane", plane);
        } else {
            Plane plane = PlaneService.getInstance().update(Long.valueOf(id), name, model, seats, costKM);
            req.setAttribute("updatedPlane", plane);
        }

        req.getRequestDispatcher("/admin/plane").forward(req, resp);
    }
}