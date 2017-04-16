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
<a href="main.jsp?user=Hello,Kim" id="back_to_page">&laquo;Back to the Main Page</a>
<%-- Section to display studies details --%> 
<%-- Clicking on Start, Stop to Participate in that study and  Edit button to display edit page and edit study details in it--%>
<section id="studies_section">
    <table id="my_studies_table" >
        <tr>
            <th>Question</th>
            <th>Action</th>
        </tr>
        <tr>
            <%-- First study details --%>
            <td>I enjoy outdoor activities</td>
            <td>
                <button type="button">Approve</button>
                <button type="button">Disapprove</button>
            </td>
        </tr>
        <%-- Second study details --%>
        <tr>
            <td></td>
            <td></td>
        </tr>
    </table>

</section>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>