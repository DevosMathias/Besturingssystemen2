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
    <title>No Test Results Overview</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="actual" value="No Test Results Overview"></jsp:param>
    </jsp:include>
    <main>

        <C:choose>
            <C:when test="${not empty persons}">
                <table>
                    <tr>
                        <th>Userid</th>
                        <th>Name</th>
                        <th id="link"><a href="/Controller?action=TestResultsOverview">Show persons without tests</a></th>
                    </tr>
                    <C:forEach var="person" items="${persons}">
                        <tr>
                            <td><C:out value="${person.userid}"/></td>
                            <td><C:out value="${person.firstName} ${person.lastName}"/></td>
                        </tr>
                    </C:forEach>
                    <caption>No Test Results Overview</caption>
                </table>
            </C:when>
            <C:otherwise>
                <p>Everyone has tested positive for covid-19 at least once.</p>
            </C:otherwise>
        </C:choose>
    </main>
    <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>


</body>
</html>
