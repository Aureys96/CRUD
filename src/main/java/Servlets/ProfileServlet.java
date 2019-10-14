package Servlets;

import Back.DataBase.DBService;
import Back.DataBase.DBServiceInt;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class ProfileServlet extends HttpServlet {
    DBServiceInt db;
    public ProfileServlet(DBServiceInt db){this.db=db;}
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Profile = request.getParameter("Profile");
        response.getWriter().println(db.getProfileByName(Profile).toString());
    }
}
