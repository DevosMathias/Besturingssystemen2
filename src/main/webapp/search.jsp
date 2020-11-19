<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: Eigenaar
  Date: 22/10/2020
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Search</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="actual" value="Contact the following people"></jsp:param>
    </jsp:include>
    <main>

        <C:if test="${not empty errors}">
            <div class="alert-danger">
                <ul>
                    <C:forEach var="error" items="${errors}">
                        <li><C:out value="${error}"/></li>
                    </C:forEach>
                </ul>
            </div>
        </C:if>

        <C:choose>
            <C:when test="${not empty contacts}">
                <table>
                    <tr>
                        <th>Name</th>
                        <th>GSM</th>
                        <th>Email</th>
                    </tr>
                <C:forEach var="contact" items="${contacts}">
                    <tr>
                        <td><C:out value="${contact.firstName} ${contact.lastName}"/></td>
                        <td><C:out value="${contact.gsm}"/></td>
                        <td><C:out value="${contact.email}"/></td>
                    </tr>
                </C:forEach>
                    <caption>Contacts Positive Test Overview</caption>
                </table>
            </C:when>
            <C:otherwise>
                <p>You haven't tested positive or there are no contacts.</p>
            </C:otherwise>
        </C:choose>
    </main>
    <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>


</body>
</html>
