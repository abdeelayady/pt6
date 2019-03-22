<%-- 
    Document   : getValues
    Created on : 05-feb-2019, 18:06:03
    Author     : abde
--%>

<%@page import="java.util.List"%>
<%@page import="models.Paciente"%>
<%@page import="models.Paciente"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <%@include file="menu.jsp" %>
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">GrupEdat</th>
                        <th scope="col">Peso</th>
                        <th scope="col">IMC</th>
                        <th scope="col">Clasificacion</th>
                    </tr>
                </thead>
                
            <%if (request.getAttribute("list") != null) {
                    List<Paciente> listado = (List<Paciente>) request.getAttribute("list");

                    for (Paciente p : listado) {
                        out.println( "<tr><th>" + p.getGrupEdat() + 
                                "</th><th>" + p.getPes() + "</th><th>" + p.getImc() +
                                "</th><th>" + p.getClassificacio() 
                                + "</th></tr>");
                    }
                }
            %>
         
        </table>
    </body>
</html>
