package Servlets;

import Back.DataBase.DBService;
import Back.DataBase.DBServiceInt;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
    DBServiceInt db;
    public SignInServlet(DBServiceInt db){this.db=db;}
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login  = request.getParameter("login");
        String password = request.getParameter("password");
        if (db.isValid(login,password)){
           /* HttpSession hs = request.getSession(true);
            hs.setAttribute("logged",true);*/
            response.setContentType("application/json; charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("Authorized: " + login);
        }
        else{
            response.setContentType("application/json; charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().println("Unauthorized");
        }
    }
}
