<%-- 
    Document   : menu
    Created on : 05-feb-2019, 17:16:42
    Author     : abde
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" 
              integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" 
              crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body>
        <nav class="navbar navbar-default">
        <%
                if (session.getAttribute("name") != null) {
                    //si la variable de session esta iniciada 
                    if (session.getAttribute("rol").equals("admin")) {
                        out.println("<a href='addUser.jsp'>Añadir Usuari</a>");
                    }

                    out.println("<a href='filtrar.jsp'>Filtrar</a>");
                    out.println(" <a href='addPaciente.jsp'>Añadir Pacient</a>");
                    out.println("<a href='logout.jsp'>Salir</a>");

                } else {
                    out.println("<a href='index.jsp'>Index</a>");
                    out.println("<a href='login.jsp'>Login</a>");
                }
        %>
        </nav>
    </body>
</html>
