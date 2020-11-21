package ui.controller;

import domain.model.Role;
import domain.model.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class TestResultsOverview extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Role[] roles = {Role.ADMINISTRATOR};
        Utility.checkRole(request, roles);

        List<Test> tests = service.getAllTests();
        request.setAttribute("tests", tests);

        return "testResultsOverview.jsp";
    }
}
