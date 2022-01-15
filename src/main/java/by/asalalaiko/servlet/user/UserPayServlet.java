package by.asalalaiko.servlet.user;

import by.asalalaiko.domain.Flight;
import by.asalalaiko.service.FlightService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(value = "/user/pay", name = "payTickets")
public class UserPayServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long flightId = Long.valueOf(req.getParameter("flight"));
        Long quantity = Long.valueOf(req.getParameter("quantity"));
        boolean baggage = req.getParameter("baggage")!= null;
        boolean priority = req.getParameter("priority")!= null;



        Flight flight = FlightService.findById(flightId);




        req.setAttribute("flight", flight);
        req.getRequestDispatcher("/user/pay.jsp").forward(req, resp);

    }

}
