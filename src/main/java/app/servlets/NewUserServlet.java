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

public class NewUserServlet extends HttpServlet {
    private DBService dbService;
    public NewUserServlet() {
        this.dbService = DBService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/admin/newUser.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Set<Authority> authorities = new HashSet<>();

        try {
            if(request.getParameter("user")!=null&&request.getParameter("admin")!=null) {
                authorities.add(AuthorityDAO.getInstance().getAuthByRole("user"));
                authorities.add(AuthorityDAO.getInstance().getAuthByRole("admin"));
                dbService.addUser(new UsersDataSet(request.getParameter("login"), request.getParameter("password"), authorities));
            }
            else if(request.getParameter("user")!=null) {
                authorities.add(AuthorityDAO.getInstance().getAuthByRole(("user")));
                dbService.addUser(new UsersDataSet(request.getParameter("login"), request.getParameter("password"), authorities));
            }
            else if(request.getParameter("admin")!=null) {
                authorities.add(AuthorityDAO.getInstance().getAuthByRole("admin"));
                dbService.addUser(new UsersDataSet(request.getParameter("login"), request.getParameter("password"), authorities));
            }
            request.setAttribute("login", request.getParameter("login"));
            request.setAttribute("password", request.getParameter("password"));
            request.setAttribute("role",authorities);
            doGet(request, response);

        } catch (DBException e) {
            e.printStackTrace();
        }

    }
}
