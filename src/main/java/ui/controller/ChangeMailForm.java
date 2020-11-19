package ui.controller;

import domain.model.Role;

import javax.rmi.CORBA.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeMailForm extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Role[] role = {Role.USER, Role.ADMINISTRATOR};
        Utility.checkRole(request, role);

        String userid = request.getParameter("userid");
        request.setAttribute("userid", userid);
        return "changeMailForm.jsp";
    }
}
