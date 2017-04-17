<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%-- title of the Page--%>
        <title>Researchers Exchange Participations</title>
        <%-- importing CSS stylesheet --%>
        <link rel="stylesheet" href="styles/main.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script type="text/javascript" src="js/scripts.js"></script>
    </head>
    <body>
        <%-- Code to specify Header section of the page--%>
        <div id="header">
                <nav id="header_menu">
                <ul  class="left" >
                    <c:choose>
                        <c:when test="${sessionScope.theUser != null || sessionScope.theAdmin != null}">
                            <li><a href="studyController">Researchers Exchange Participations</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="home.jsp">Researchers Exchange Participations</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
                <ul class="right">
                    <c:choose>
                        <c:when test="${sessionScope.theUser != null}">
                            <li>Hello, <span>${theUser.name}</span></li>
                        </c:when>
                        <c:when test="${sessionScope.theAdmin != null}">
                        <li>Hello, <span>${theAdmin.name}</span></li>
                        </c:when>
                    </c:choose>
                    <li><a href="userController?action=about">About Us</a></li>
                    <li><a href="userController?action=how">How it Works</a></li>
                    <c:choose>
                        <c:when test="${sessionScope.theUser == null && sessionScope.theAdmin == null}">
                            <li><a href="login.jsp">Login</a></li>
                        </c:when>   
                        <c:otherwise>
                            <li><a href="userController?action=logout">Log Out</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>

            </nav>



        </div>

