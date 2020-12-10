package app.filters;

import app.DBService.DBException;
import app.DBService.DBService;
import app.dao.AuthorityDAO;
import app.entities.Authority;
import app.entities.UsersDataSet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class AdminFilter implements Filter {
    AuthorityDAO authorityDAO = AuthorityDAO.getInstance();
    DBService dbService = DBService.getInstance();
//    Set<String> allowed = Collections.unmodifiableSet(new HashSet<>(
//            Arrays.asList("/admin/allUsers", "/admin/deleteUser", "/admin/editUser", "/admin/newUser")));
    @Override
    public void init(FilterConfig config) throws ServletException {
    }
//    boolean isAllowed(HttpServletRequest request) {
//        String path = request.getRequestURI();
//        String extension = path.substring(path.indexOf('.', path.lastIndexOf('/')) + 1);
//        return allowed.contains(extension);
//    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        try {
            long id =  (long) session.getAttribute("id");
            UsersDataSet usersDataSet = dbService.getUserById(id);
            Set<Authority> authorities = usersDataSet.getAuthorities();

            if (authorities.contains(authorityDAO.getAuthByRole("admin"))) {
                filterChain.doFilter(request, response);
            } else {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error");
                requestDispatcher.forward(request, response);
            }
        } catch (DBException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void destroy() {
    }
}
