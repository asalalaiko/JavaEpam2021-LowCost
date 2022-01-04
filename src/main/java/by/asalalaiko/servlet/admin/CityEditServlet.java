package by.asalalaiko.servlet.admin;


import by.asalalaiko.domain.City;
import by.asalalaiko.service.CityService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(value = "/admin/city/edit", name = "cityEditAdmin")
public class CityEditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");

        City city = CityService.getInstance().getById(Long.valueOf(id));

        req.setAttribute("city", city);

        Collection<City> all = CityService.findAll();

        req.setAttribute("cities", all);


        req.getRequestDispatcher("/admin/city.jsp").forward(req, resp);

    }
}

