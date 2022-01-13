package by.asalalaiko.servlet.cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;

@WebServlet(value = "/cart/del", name = "delToCart")
public class DelToCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));

        try {
            HashSet<Long> flights  =(HashSet<Long>) req.getSession().getAttribute("cartFlights");
            if( flights != null) {
                flights.removeIf(k -> k.equals(id));
                req.getSession().setAttribute("cartFlights", flights);
            }
            req.getRequestDispatcher("/cart").forward(req, resp);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }
}
