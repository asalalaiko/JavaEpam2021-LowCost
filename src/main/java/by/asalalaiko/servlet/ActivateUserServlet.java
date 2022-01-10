package by.asalalaiko.servlet;

import by.asalalaiko.domain.City;
import by.asalalaiko.service.CityService;
import by.asalalaiko.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/activate", name = "activateUser")
public class ActivateUserServlet  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String code = req.getParameter("code");

        req.setAttribute("user", UserService.getInstance().activateUser(code));

        req.getRequestDispatcher("/activate.jsp").forward(req, resp);
    }
}
