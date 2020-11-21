package ui.controller;

import com.sun.java.swing.plaf.windows.WindowsDesktopIconUI;
import domain.model.ContactTracingService;
import domain.model.DomainException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Controller")
public class controller extends HttpServlet {
    private ContactTracingService service = new ContactTracingService();
    private HandlerFactory handlerFactory = new HandlerFactory();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NotAuthorizedException {
        String command = request.getParameter("action");
        String destination = "index.jsp";

        String redirect = request.getParameter("redirect");

        if (command != null) {
            try {
                RequestHandler handler = handlerFactory.getHandler(command, service);
                destination = handler.handleRequest(request, response);
            } catch (NotAuthorizedException e) {
                request.setAttribute("notAuthorized", "You have insufficient rights to have a look at the requested page.");
                destination = "index.jsp";
                //handlerFactory.getHandler("Home", service).handleRequest(request, response);
            }
            catch (Exception e) {
                throw new IllegalArgumentException(e);
                /*request.setAttribute("error", e.getMessage());
                destination = "error.jsp";*/
            }
        }

        if (destination.endsWith("Redirect")) {
            response.sendRedirect(destination.substring(0, destination.length()-8));
        } else {
            RequestDispatcher view = request.getRequestDispatcher(destination);
            view.forward(request, response);
        }
    }
}
