package ui.controller;

import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogIn extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

        String userid = request.getParameter("userid").toLowerCase();
        String password = request.getParameter("password");

        Person person = service.get(userid); //nog try catch doen???

        if (person != null && person.isCorrectPassword(password)) {
            request.getSession().setAttribute("personLogIn", person);
        } else {
            request.setAttribute("error", "No valid userid/password");
        }
        return "index.jsp";
    }
}