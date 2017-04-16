<%-- 
    Document   : main
    Created on : Sep 19, 2015, 9:17:56 PM
    Author     : Suman
--%>
<%@page import="Business.Study"%>
<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<%-- Code to display Page Name --%>
<h3 id="page_name">My Studies</h3>
 <%-- Code to add new study   --%>
<h3 id="add_new_study"><a href="newstudy.jsp" >Add a new study</a></h3>
 <%-- Code to go Back to the Main Page  --%>
<a href="main.jsp?user=Hello,Kim" id="back_to_page">&laquo;Back to the Main Page</a>
<%-- Section to display studies details --%> 
<%-- Clicking on Start, Stop to Participate in that study and  Edit button to display edit page and edit study details in it--%>
<section id="studies_section">


    <table id="my_studies_table" >
        <tr>
            <th>Study Name</th>
            <th>Requested Participants</th>		
            <th>Participations</th>
            <th>Study Code</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${studies}" var="study">
            <%
                Study study = new Study();
                String studyCode = study.getStudyCode();
                request.setAttribute("StudyCode", studyCode);
                %>
            <tr>
                <td>${study.studyName}</td>
                <td>${study.requestedParticipants}</td>
                <td>${study.numofParticipants}</td>
                <td name="StudyCode">${study.studyCode}</td>
                <form action="studyController" method="post">
                    <td>
                        <button type="submit" name="action" value="start">Start</button>
                    </td>
                    <td>
                        <button type="submit" name="action" value="edit">Edit</button>
                    </td>
                 </form>
            </tr>
        </c:forEach>
    </table>

</section>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>