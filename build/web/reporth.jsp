<%-- 
    Document   : reporth
    Created on : Feb 23, 2017, 6:40:50 PM
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
            <th>Report Date</th>
            <th>Report Question</th>
            <th>Report Status</th>
        </tr>
        <tr>
            <%-- First study details --%>
            <td>01/15/2016</td>
            <td>How much do you...</td>
            <td>Approved</td>
        </tr>
        <%-- Second study details --%>
        <tr>
            <td>01/18/2016</td>
            <td>What do you do w..</td>
            <td>Pending</td>
        </tr>
    </table>

</section>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>