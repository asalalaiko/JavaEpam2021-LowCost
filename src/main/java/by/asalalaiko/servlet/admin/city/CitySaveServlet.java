package by.asalalaiko.servlet.admin.city;

import by.asalalaiko.domain.City;
import by.asalalaiko.service.CityService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/city/save", name = "citySaveAdmin")
public class CitySaveServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        if (id == "") {
            City city = CityService.getInstance().create(name);
            req.setAttribute("createdCity", city);
        } else {
            City city = CityService.getInstance().update(Long.valueOf(id), name);
            req.setAttribute("updatedCity", city);
        }

        req.getRequestDispatcher("/admin/city").forward(req, resp);
    }
}
