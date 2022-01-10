package by.asalalaiko.servlet;

import by.asalalaiko.service.UserService;
import by.asalalaiko.service.VerifyRecaptchaServise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/register", name = "register")
public class RegisterUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login").trim();
        String password = req.getParameter("password").trim();
        String email = req.getParameter("email").trim();
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");


        String gRecaptchaResponse = req.getParameter("g-recaptcha-response");
        boolean verify = VerifyRecaptchaServise.isCaptchaValid("6Lc_mwQeAAAAAPAt7FGJhSM1O94J_p7tFojNcMWl",
                gRecaptchaResponse);


        if(login.equals("") || password.equals("") || email.equals("")) {
            String errorMessage = "Invalid Login or Password or email";
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }

        if(UserService.getInstance().findByLogin(login) != null){
            String errorMessage = "User with this login is already registered";
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }

        if (!verify) {
            String errorMessage = "You missed the Captcha";
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
              }

        req.getSession().setAttribute("user",
                UserService.getInstance().registerUser(login, password, fname, lname, email));

        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }



}
