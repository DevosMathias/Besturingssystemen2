package ui.controller;

import domain.model.Contact;
import domain.model.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ContactOverview extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

        Role[] roles = {Role.ADMINISTRATOR, Role.USER};
        Utility.checkRole(request, roles);

        String showUniqueContacts = request.getParameter("value");
        List<Contact> contacts = null;

        if (showUniqueContacts == null || showUniqueContacts.equals("no")) {
            request.setAttribute("showUniqueContacts", "no");
            contacts = service.getAllContacts();
        } else {
            request.setAttribute("showUniqueContacts", "yes");
            contacts = service.getUniqueContact();
            for (Contact c : contacts) {
                System.out.println(c.getEmail());
            }
        }

        request.setAttribute("contacts", contacts);
        return "contactoverview.jsp";
    }

}
