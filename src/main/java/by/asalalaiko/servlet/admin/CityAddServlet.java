package by.asalalaiko.servlet.admin;

import by.asalalaiko.domain.City;
import by.asalalaiko.service.CityServise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/city/save", name = "citySaveAdmin")
public class CityAddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        if (id == null) {
            City city = CityServise.getInstance().create(name);
            req.setAttribute("createdCity", city);
        } else {
            City city = CityServise.getInstance().update(Long.valueOf(id), name);
            req.setAttribute("updatedCity", city);
        }

        req.getRequestDispatcher("cityListAdmin").forward(req, resp);
    }
}
