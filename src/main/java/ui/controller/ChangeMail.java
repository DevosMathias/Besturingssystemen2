package ui.controller;

import domain.model.Person;
import domain.model.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeMail extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Role[] roles = {Role.USER, Role.ADMINISTRATOR};
        Utility.checkRole(request, roles);


        //String userid = request.getParameter("userid");
        String newMail = request.getParameter("newMail");
        String error;
        try {
            Person person = (Person) request.getSession().getAttribute("personLogIn");
            person.setEmail(newMail);

            //Persoon wegschrijven of mail wegschrijven???
            this.service.changeMailPerson(person.getUserid(), newMail);
            return "index.jsp";
        } catch (Exception e) {
            error = e.getMessage();
        }

        request.setAttribute("error", error);
        //request.setAttribute("userid", userid);
        return "Controller?action=ChangeMailForm";
    }
}
