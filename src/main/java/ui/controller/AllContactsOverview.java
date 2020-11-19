package ui.controller;

import domain.model.Contact;
import domain.model.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AllContactsOverview extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Role[] roles = {Role.ADMINISTRATOR};
        Utility.checkRole(request, roles);

        List<Contact> contacts = service.getAllContactsAdmin();
        request.setAttribute("contacts", contacts);
        return "allContactsOverview.jsp";
    }
}
