package Servlets;

import Back.DataBase.DBServiceInt;

import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class ProfileServlet extends HttpServlet {
    DBServiceInt db;
    public ProfileServlet(DBServiceInt db){this.db=db;}
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String Profile = request.getParameter("Profile");
            response.getWriter().println(db.getProfileByName(Profile).toString());
        }
        catch (NoResultException e){
            response.getWriter().println("Requested profile does not exist");
        }
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Profile = request.getParameter("ProfileToUpdate");
        String newProfileName = request.getParameter("NewProfile");
        db.updateName(newProfileName,Profile);
        response.getWriter().println("Profile "+Profile+" is updated to new name: "+newProfileName);

    }
    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String Profile = request.getParameter("Profile");
        db.delete(Profile);
        response.getWriter().println("Profile "+'"'+Profile+"'"+" was deleted");
    }

}
