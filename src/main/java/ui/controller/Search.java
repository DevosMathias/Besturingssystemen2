package ui.controller;

import domain.model.Role;
import sun.awt.geom.AreaOp;

import javax.rmi.CORBA.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Search extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Role[] roles = {Role.ADMINISTRATOR, Role.USER};
        Utility.checkRole(request, roles);

        return "search.jsp";
    }
}
