package by.asalalaiko.servlet.admin;

import by.asalalaiko.repo.UserRepo;
import by.asalalaiko.service.CityService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(value = "/admin/city/del", name = "cityDeleteAdmin")
public class CityDelServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameter = req.getParameter("id");

        Long cityId = Long.valueOf(parameter);
        try {

            CityService.deleteById(cityId);

            req.getRequestDispatcher("/admin/city").forward(req, resp);

        } catch (Exception e) {
            throw new IOException(e);
        }

    }
}

