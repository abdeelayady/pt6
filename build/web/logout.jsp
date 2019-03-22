<%-- 
    Document   : logout
    Created on : 20-mar-2019, 17:02:18
    Author     : abde
--%>

<%
    session.invalidate();
    response.sendRedirect("index.jsp");

%>