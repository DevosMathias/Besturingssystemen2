package ui.controller;

import domain.model.Person;
import domain.model.Role;

import javax.servlet.http.HttpServletRequest;

/**
 * Checks if the roleof the user stores in request.getSession() has one of the given roles
 * @throws NotAuthorizedException if the role of the user does not correspond with one of the given roles
 */
public class Utility {
    public static void checkRole(HttpServletRequest request, Role[] roles) {
        Person person = (Person) request.getSession().getAttribute("personLogIn");

        if (person == null) {
            throw new NotAuthorizedException();
        }

        boolean found = false;
        for (int i = 0; i < roles.length && !found; i++) {
            if (person.getRole().equals(roles[i])) {
                found = true;
            }
        }

        if (!found) {
            throw new NotAuthorizedException();
        }

    }
}
