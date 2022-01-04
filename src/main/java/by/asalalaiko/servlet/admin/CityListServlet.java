package by.asalalaiko.servlet.admin;

import by.asalalaiko.domain.City;
import by.asalalaiko.domain.User;
import by.asalalaiko.repo.CityRepo;
import by.asalalaiko.repo.UserRepo;
import by.asalalaiko.service.CityService;
import by.asalalaiko.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(value = "/admin/city", name = "cityListAdmin")
public class CityListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            Collection<City> all = CityService.findAll();

            req.setAttribute("cities", all);

            req.getRequestDispatcher("/admin/city.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new IOException(e);
        }
    }


}
