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
import java.util.HashSet;
import java.util.Set;

public class SignUpServlet extends HttpServlet {
    private DBService dbService;

    public SignUpServlet() {
        this.dbService = DBService.getInstance();
    }

   @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/signUp.jsp");
        requestDispatcher.forward(request, response);
    }

   @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) {
        Set<Authority> authorities = new HashSet<>();

        try {

            authorities.add(AuthorityDAO.getInstance().getAuthByRole("user"));
            UsersDataSet usersDataSet = new UsersDataSet(request.getParameter("login"), request.getParameter("password"), authorities);
            dbService.addUser(usersDataSet);
            request.setAttribute("login", request.getParameter("login"));
            request.setAttribute("password", request.getParameter("password"));
            request.getSession().setAttribute("id",usersDataSet.getId());
//            request.setAttribute("role",authorities);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/firstPage.jsp");
            requestDispatcher.forward(request, response);

        } catch (DBException | ServletException | IOException e) {
            e.printStackTrace();
        }
    }

}

