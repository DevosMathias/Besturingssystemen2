<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Test Result</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="actual" value="Register Test Result"></jsp:param>
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


        <form method="POST" action="Controller?action=" novalidate="novalidate">
            <!-- novalidate in order to be able to run tests correctly -->
            <p><label for="date">Date</label>
                <input type="date" id="date" name="date" value="" required /> </p>

            <p><input type="submit" id="registerTest" value="Covid-19 positive"></p>

        </form>
    </main>
    <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>
