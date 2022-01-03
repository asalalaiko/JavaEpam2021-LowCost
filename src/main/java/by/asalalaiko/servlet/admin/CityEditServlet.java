package by.asalalaiko.servlet.admin;


import by.asalalaiko.service.CityServise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/city/edit", name = "cityEditAdmin")
public class CityEditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameter = req.getParameter("id");

        Long cityId = Long.valueOf(parameter);
        try {

       //     CityServise.deleteById(cityId);

            req.getRequestDispatcher("cityListAdmin").forward(req, resp);

        } catch (Exception e) {
            throw new IOException(e);
        }

    }
}

