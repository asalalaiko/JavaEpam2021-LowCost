package by.asalalaiko.filter;

import by.asalalaiko.domain.User;
import by.asalalaiko.domain.UsersRole;
import by.asalalaiko.exception.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        User user = new User();

        try {
            HttpSession session = req.getSession(false);
            if ( session!= null) { user = (User) session.getAttribute("user");}

            boolean loggedIn = session != null && user.getRole() == UsersRole.ADMIN;

            if(loggedIn) {
                chain.doFilter(req, res);
            }

            else {
                res.sendRedirect("/JavaEpam2021_LowCost_war/login.jsp");
            }
        } catch (UnauthorizedException e) {
            LOGGER.error("Operation cannot be performed for unauthorized user", e);
            ((HttpServletResponse) response)
                    .sendRedirect(((HttpServletRequest) request).getContextPath() + "/login.jsp");
        } catch (Exception e) {
            LOGGER.error("something went wrong", e);
            ((HttpServletResponse) response)
                    .sendRedirect(((HttpServletRequest) request).getContextPath() + "/error.jsp");
        }
    }
}
