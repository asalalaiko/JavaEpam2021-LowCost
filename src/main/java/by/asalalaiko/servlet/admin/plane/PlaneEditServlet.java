package by.asalalaiko.servlet.admin.plane;


import by.asalalaiko.domain.Plane;
import by.asalalaiko.service.PlaneService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(value = "/admin/plane/edit", name = "planeEditAdmin")
public class PlaneEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");

        Plane plane = PlaneService.getInstance().getById(Long.valueOf(id));

        req.setAttribute("plane", plane);

        Collection<Plane> all = PlaneService.findAll();

        req.setAttribute("planes", all);


        req.getRequestDispatcher("/admin/plane.jsp").forward(req, resp);

    }

}
