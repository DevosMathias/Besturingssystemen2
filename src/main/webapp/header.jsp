<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <h1><!--<span>Tracing App</span>--></h1>
    <nav>
        <ul>
            <li ${param.actual eq 'Home'?"id=actual":""}>
                <a href="Controller">Home</a></li>
            <C:if test="${personLogIn.role == 'ADMINISTRATOR'}">
                <li ${param.actual eq 'User Overview'?"id=actual":""}>
                    <a href="Controller?action=Overview">Overview</a></li>
            </C:if>
            <C:if test="${personLogIn.role == 'ADMINISTRATOR' || personLogIn.role == 'USER'}">
                <li ${param.actual eq 'Contact Overview'?"id=actual":""}>
                    <a href="Controller?action=ContactOverview">Contacts</a></li>
                <li ${param.actual eq 'Register Test Result'?"id=actual":""}>
                    <a href="Controller?action=TestResultForm">Register Test Result</a></li>
                <li ${param.actual eq 'Contact the following people'?"id=actual":""}>
                    <a href="Controller?action=Search">Search</a></li>
            </C:if>
            <li ${param.actual eq 'Register'?"id=actual":""}>
                <a href="Controller?action=Register">Register</a></li>
        </ul>
        <h2>${param.actual}</h2>
    </nav>
</header>