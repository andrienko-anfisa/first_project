package app.filters;

import app.DBService.DBException;
import app.DBService.DBService;
import app.entities.Authority;
import app.entities.UsersDataSet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/*
В этом фильтре должна осуществляться проверка на то что пользователь либо зашёл в систему,
либо зарегестрировался в системе и далее перенаправление на AdminFilter для дальнейшей проверки прав пользователя
 */
public class AuthorizationFilter implements Filter {
    Set<String> allowed = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("/signIn", "/signUp","/")));

    public void init(FilterConfig config){
    }
    boolean isAllowed(HttpServletRequest request) {
        String path = request.getRequestURI();
        String extension = path.substring(path.indexOf('.', path.lastIndexOf('/')) + 1).toLowerCase();
        return allowed.contains(extension);
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
       HttpSession session = req.getSession();

        if (session.getAttribute("id") == null && isAllowed(req)) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/signIn");
            requestDispatcher.forward(request, response);
        }else {
            filterChain.doFilter(request, response);
        }
    }

    public void destroy() {
    }
}
