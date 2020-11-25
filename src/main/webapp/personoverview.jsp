<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
<title>Overview</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <div id="container">
        <jsp:include page="header.jsp">
            <jsp:param name="actual" value="User Overview"></jsp:param>
        </jsp:include>
        <main>
            <table>
                <tr>
                    <th>E-mail</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                </tr>

                <C:forEach var="person" items="${persons}">
                    <tr>
                        <td><C:out value="${person.email}"/></td>
                        <td><C:out value="${person.firstName}"/></td>
                        <td><C:out value="${person.lastName}"/></td>
                    </tr>
                </C:forEach>

                <caption>Users Overview</caption>
            </table>
    </main>
    <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
    </div>
</body>
</html>