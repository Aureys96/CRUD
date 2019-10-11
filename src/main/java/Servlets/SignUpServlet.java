package Servlets;

import Back.DataBase.DBService;
import Back.DataBase.DBServiceInt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {
    private DBServiceInt db;
    public SignUpServlet(DBServiceInt db){this.db=db;}
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        db.addProfile(request.getParameter("login"), request.getParameter("password"),request.getParameter("email"));
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("Registration complete: " + request.getParameter("login"));
    }
}
