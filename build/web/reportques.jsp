<%-- 
    Document   : reportques
    Created on : Feb 23, 2017, 6:27:26 PM
    Author     : Stephen Weber
--%>

<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<%-- Code to display Page Name --%>
<h3 id="page_name">Reported Questions</h3>
 <%-- Code to go Back to the Main Page  --%>
<a href="main.jsp" id="back_to_page">&laquo;Back to the Main Page</a>
<%-- Section to display studies details --%> 
<%-- Clicking on Start, Stop to Participate in that study and  Edit button to display edit page and edit study details in it--%>
<section id="studies_section">
    <table id="my_studies_table" >
        <tr>
            <th>Question</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${reportedStudies}" var="study">
            <tr>
                <td>${study.question}</td>
                <form action="studyController" method="post">
                    <input type="hidden" name="StudyCode" value="${study.studyCode}"/>
                    <td>
                        <button type="submit" name="action" value="approve">Approve</button>
                        <button type="submit" name="action" value="disapprove">Disapprove</button>
                    </td>
                </form>
            </tr>
        </c:forEach>
    </table>

</section>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>