<%-- 
    Document   : page
    Created on : 05-feb-2019, 17:30:05
    Author     : abde
--%>

<%@page import="models.Paciente"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
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
         <%@include file="menu.jsp" %>
        <table class="table">
            <caption>Pacients list</caption>
            <tr>
                <th>Edad</th>
                <th>Grupo edad</th>
                <th>Pes</th>
                <th>Talla</th>
                <th>IMC</th>
                <th>Clasificacion</th>
                <th>Menarquia</th>
                <th>Menopausia</th>
                <th>Tipo menopausia</th>
            </tr>
            <%
                if(session.getAttribute("list")!=null){
                    List<Paciente> pacient_list = (List<Paciente>)session.getAttribute("list");
                    
                        for (Paciente o : pacient_list){
                        out.println("<tr><td>"+o.getEdat()+"</td>"
                                + "<td>"+o.getGrupEdat()+"</td><td>"+o.getPes()+"</td>"
                                + "<td>"+o.getTalla()+"</td><td>"+o.getImc()+"</td>"
                                + "<td>"+o.getClassificacio()+"</td><td>"+o.getMenarquia()+"</td>"
                                + "<td>"+o.getMenopausia()+"</td><td>"+o.getTipusMenopausia()+"</td>");
                        out.println("</tr>");
                    }
                }
                else {
                    out.println("Error al cargar los datos");
                }
            %>
        </table>
    </body>
</html>
