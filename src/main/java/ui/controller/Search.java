package ui.controller;

import domain.model.Contact;
import domain.model.Person;
import domain.model.Role;
import domain.model.Test;
import sun.awt.geom.AreaOp;

import javax.rmi.CORBA.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class Search extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Role[] roles = {Role.ADMINISTRATOR, Role.USER};
        Utility.checkRole(request, roles);

        Person person = (Person) request.getSession().getAttribute("personLogIn");
        String userid = person.getUserid();
        List<Contact> contacts = new ArrayList<>();

        Test lastTest = service.getTestLastPerson(userid);

        if (lastTest != null) {
            contacts = service.getAllContactsPersonAfterDate(userid, lastTest.getDate());
        }

        request.setAttribute("contacts", contacts);

        return "search.jsp";
    }
}
