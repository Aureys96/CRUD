import Back.DataBase.DBService;
import Back.DataBase.DBServiceInt;

import Servlets.*;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.server.handler.*;
import org.eclipse.jetty.servlet.*;

public class Main {
    public static void main(String[] args) throws Exception{
        DBServiceInt db = new DBService();
        db.printConnectInfo();
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new SignUpServlet(db)), "/SignUp");
        context.addServlet(new ServletHolder(new SignInServlet(db)), "/SignIn");
        context.addServlet(new ServletHolder(new ProfileServlet(db)), "/Profile");
        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("src\\main\\resources");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});
        Server server = new Server(8080);
        server.setHandler(handlers);
        server.start();
        server.join();
    }
}
