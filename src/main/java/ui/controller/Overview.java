package ui.controller;

import domain.model.Person;
import domain.model.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class Overview extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Role[] roles = {Role.ADMINISTRATOR};
        Utility.checkRole(request, roles);

        List<Person> persons = service.getAllPersons();
        request.setAttribute("persons", persons);
        return "personoverview.jsp";
    }
}
