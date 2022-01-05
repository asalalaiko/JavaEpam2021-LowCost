package by.asalalaiko.servlet.admin.plane;


import by.asalalaiko.service.CityService;
import by.asalalaiko.service.PlaneService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/plane/del", name = "planeDeleteAdmin")
public class PlaneDelServlet  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameter = req.getParameter("id");

        Long id= Long.valueOf(parameter);
        try {

            PlaneService.deleteById(id);

            req.getRequestDispatcher("/admin/plane").forward(req, resp);

        } catch (Exception e) {
            throw new IOException(e);
        }

    }

}
