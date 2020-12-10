package app.servlets;

import app.DBService.DBService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteUserServlet extends HttpServlet {

    private DBService dbService;

    public DeleteUserServlet() {

        this.dbService = DBService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        dbService.deleteUser(id);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/admin/deleteUser.jsp");
        requestDispatcher.forward(request, response);
    }
}
