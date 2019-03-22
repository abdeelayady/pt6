<%-- 
    Document   : result_filtro
    Created on : 20-mar-2019, 17:36:43
    Author     : abde
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="utils.CreatePDF"%>
<%@page import="java.util.List"%>
<%@page import="models.Paciente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>Filtro</title>
    </head>
    <body>
        <%@include file="menu.jsp"%>
        <br>
        <div class="container">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Edad</th>
                        <th scope="col">Grupo Edad</th>
                        <th scope="col">Peso</th>
                        <th scope="col">Altura</th>
                        <th scope="col">IMC</th>
                        <th scope="col">Clasificacion</th>
                        <th scope="col">Menarquia</th>
                        <th scope="col">Menopausia</th>
                        <th scope="col">Tipo de Menopausia</th>
                    </tr>
                </thead>
                <tbody>
                    <%                       
                        if (request.getAttribute("filter") != null) {
                            //recojo por post el parametro
                            List<Paciente> listado = (List<Paciente>) request.getAttribute("filter");

                            for (Paciente o : listado) {
                                out.println("<tr><td>" + o.getEdat() + "</td>"
                                        + "<td>" + o.getGrupEdat() + "</td><td>" + o.getPes() + "</td>"
                                        + "<td>" + o.getTalla() + "</td><td>" + o.getImc() + "</td>"
                                        + "<td>" + o.getClassificacio() + "</td><td>" + o.getMenarquia() + "</td>"
                                        + "<td>" + o.getMenopausia() + "</td><td>" + o.getTipusMenopausia() + "</td>");
                                out.println("</tr>");
                            }
                        } else {
                            out.println("Error al cargar los datos");
                        }
                    %>
                </tbody>
            </table>
        </div>
        <a href="filtrar.jsp" class="btn btn-primary">Back</a>
        
        <form method="post" action="result_filtro.jsp">
            <input class="form-text text-muted" type="submit" name="ok" value="Exportar pdf"/>
        </form>  
        <%
           //si ha clicat o no al botó del formulari
          if(request.getParameter("ok")!=null) { 
          CreatePDF pdf = new CreatePDF();
          List<Paciente> list = (List<Paciente>) session.getAttribute("listapdf");

          pdf.createpdf(list);
          }
          
        %>
    </body>
</html>
