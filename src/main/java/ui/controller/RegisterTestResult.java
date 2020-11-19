package ui.controller;

import domain.model.Person;
import domain.model.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RegisterTestResult extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

        Test test = new Test();
        ArrayList<String> errors = new ArrayList<>();
        setUserid(request, test, errors);
        setDate(request, test, errors);

        if (errors.size() == 0) {
            try {
                service.addTest(test);
                return "Controller?action=ContactOverview";
            } catch (Exception e) {
                errors.add(e.getMessage());
                System.out.println(e.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return "testResultForm.jsp";
    }

    public void setUserid(HttpServletRequest request, Test test, ArrayList<String> errors) {
        try {
            Person person = (Person) request.getSession().getAttribute("personLogIn");
            String userid = person.getUserid();
            test.setUserid(userid);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    public void setDate(HttpServletRequest request, Test test, ArrayList<String> errors) {
        try {
            String date = request.getParameter("date");
            if (date == null || date.isEmpty()) {
                throw new IllegalArgumentException("No valid date given");
            }
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);
            test.setDate(localDate);

        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }
}