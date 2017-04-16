<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<%-- Code to display items in List --%>
<nav id="menu">
    <ul>
        <li>Coins (<span class="count">2</span>) </li>
        <li>Participants (<span class="count">3</span>) </li>
        <li>Participation (<span class="count">5</span>) </li>
        <li><br></li>
        <li><a href="userController?action=home">Home</a></li>
        <li><a href="studyController?action=play">Participate</a></li>
        <li><a href="studyController?action=studies">My Studies</a></li>
        <li><a href="recommend.jsp">Recommend</a></li>
        <li><a href="contact.jsp">Contact</a></li>
    </ul>

</nav>
<%-- Section to display studies and participate in that study--%>
<section class="participate">
    <ul>
        <li><h3><span id="studies">Studies</span></h3></li>
        <li><h3><span id="studies"><a id="reportHistory" href="reporth.jsp">Report History</a></span></h3></li>
    </ul>
    <%-- Display the studies in the table --%>
    <%-- Clicking on Participate button displays Question.jsp page where 
            you can rate the question--%>
    <table id="studies_table" >
        <%--Column Names --%>
        <tr>
            <th>Study Name</th>
            <th>Image</th>		
            <th>Question</th>
            <th>Action</th>
            <th>Report</th>
        </tr>
        <c:forEach items="${studies}" var="study">
            <tr>
                <td>${study.studyName}</td>
                <td><img src="${study.imageURL}" alt="">${study.imageURL}</td>
                <td>${study.question}</td>
                <td>
                    <form action="studyController" method="post">
                        <input type="submit" class="participate_button" name="action" value="participate" />
                    </form>
                </td>
                <td>
                    <form action="studyController" method="post">
                        <input type="submit" class="confirm_button" name="action" value="report" />
                    </form>
                </td>   
            </tr>
        </c:forEach>
    </table>

</section>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>