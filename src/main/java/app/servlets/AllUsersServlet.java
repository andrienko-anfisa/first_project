package app.servlets;

import app.DBService.DBException;
import app.DBService.DBService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AllUsersServlet extends HttpServlet {
    private DBService dbService;

    public AllUsersServlet() {
        this.dbService = DBService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            request.setAttribute("users", dbService.getAllUsers());//создаём аттрибут который взял в себя всё что есть в базе данных
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/admin/allUsers.jsp");
            requestDispatcher.forward(request, response);
        } catch (DBException e) {
            e.printStackTrace();
        }

    }
}
