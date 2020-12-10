package app.servlets;


import app.DBService.DBException;
import app.DBService.DBService;
import app.dao.AuthorityDAO;
import app.entities.Authority;
import app.entities.UsersDataSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class SignInServlet extends HttpServlet {
private DBService dbService;

    public SignInServlet() {
        this.dbService = DBService.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/signIn.jsp");
        requestDispatcher.forward(request, response);
    }

   @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException, ServletException {
        UsersDataSet profile = null;
        try {
            //todo допилить страничку если пользователь ввел несуществующие данные
            profile = dbService.getUserByLogin(request.getParameter("login"));

            if (profile == null) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/error.jsp");
                requestDispatcher.forward(request, response);
                return;
            } else {
                request.getSession().setAttribute("id",profile.getId());
                Set<Authority> authorities = profile.getAuthorities();

                AuthorityDAO authorityDAO = AuthorityDAO.getInstance();
                if (authorities.contains(authorityDAO.getAuthByRole("user"))&&authorities.contains(authorityDAO.getAuthByRole("admin"))) {
                    request.getSession().setAttribute("isAdmin", true);
                    request.getSession().setAttribute("isUser", true);
                } else if (authorities.contains(authorityDAO.getAuthByRole("user"))) {
                    request.getSession().setAttribute("isAdmin", false);
                    request.getSession().setAttribute("isUser", true);
                } else if (authorities.contains(authorityDAO.getAuthByRole("admin"))) {
                    request.getSession().setAttribute("isAdmin", true);
                    request.getSession().setAttribute("isUser", false);
                }
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
       RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/firstPage.jsp");
       requestDispatcher.forward(request, response);
    }

}

