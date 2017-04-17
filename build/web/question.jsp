<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<%-- Code to display items in List --%>
<nav id="menu">
    <ul>
        <li>Coins (<span class="count">${theUser.numCoins}</span>) </li>
        <li>Participants (<span class="count">${part}</span>) </li>
        <li>Participation (<span class="count">${theUser.numParticipation}</span>) </li>
        <li><br></li>
        <li><a href="userController?action=home">Home</a></li>
        <li><a href="studyController?action=play">Participate</a></li>
        <li><a href="studyController?action=studies">My Studies</a></li>
        <li><a href="recommend.jsp">Recommend</a></li>
        <li><a href="contact.jsp">Contact</a></li>
    </ul>
</nav>
<%-- Code to Display Question--%>
<section class="question_section">
    <h3><span id="studies">${study.question}</span></h3>
    <img src="${study.imageURL}" id="question_page_image" alt=""/>

<%--Code to rating the Question --%>
    <form action="studyController" method="post">
        <div id="question_select"><p>${study.description}</p>
            <input type="hidden" name="StudyCode" value="${study.studyCode}"/>
            <input id="radio" type="radio" name="choice" value="one">1<br/>
            <input id="radio" type="radio" name="choice" value="two">2<br/>
            <input id="radio" type="radio" name="choice" value="three">3<br/>
        </div>
        <div id="question_submit_div"> 
            <button type="submit" id="question_submit" name="action" value="answer">Submit</button>    
        </div>
    </form>
</section>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>