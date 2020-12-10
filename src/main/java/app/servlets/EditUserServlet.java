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

public class EditUserServlet extends HttpServlet {
    private DBService dbService;
    public EditUserServlet() {
        this.dbService = DBService.getInstance();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        try {
            request.setAttribute("login", dbService.getUserById(id).getLogin());
            request.setAttribute("password", dbService.getUserById(id).getPassword());
            request.setAttribute("id", id);
//            todo устанавливать права в зависимости от имеющихся?
        } catch (DBException e) {
            e.printStackTrace();
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/admin/editUser.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Set<Authority> authorities = new HashSet<>();

        if(request.getParameter("user")!=null&&request.getParameter("admin")!=null) {
            authorities.add(AuthorityDAO.getInstance().getAuthByRole("user"));
            authorities.add(AuthorityDAO.getInstance().getAuthByRole("admin"));
            dbService.editUser(new UsersDataSet(id,request.getParameter("login"),request.getParameter("password"),authorities));
        }
        else if(request.getParameter("user")!=null) {
            authorities.add(AuthorityDAO.getInstance().getAuthByRole("user"));
            dbService.editUser(new UsersDataSet(id,request.getParameter("login"),request.getParameter("password"),authorities));
        }
        else if(request.getParameter("admin")!=null) {
            authorities.add(AuthorityDAO.getInstance().getAuthByRole("admin"));
            dbService.editUser(new UsersDataSet(id,request.getParameter("login"),request.getParameter("password"),authorities));
        }
        request.setAttribute("id", id);
        String login = request.getParameter("login");
        request.setAttribute("login", login);
        request.setAttribute("password", request.getParameter("password"));
        request.setAttribute("role",authorities);
        try {
            request.setAttribute("users", dbService.getAllUsers());
        } catch (DBException e) {
            e.printStackTrace();
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/admin/allUsers.jsp");
        requestDispatcher.forward(request, response);
    }
}
