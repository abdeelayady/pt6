<%-- 
    Document   : filtrar
    Created on : 19-mar-2019, 17:35:20
    Author     : abde
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <br>
        <form class="px-4 py-3" method="post" action="osteo">
            <label>Classificacion</label>
            <select class="form-control" name="clasificacion">
                <option selected value=""> --- </option>
                <option value="osteopenia">Osteopenia</option>
                <option value="normal">Normal</option>
                <option value="osteoporosi">Osteoporosis</option>
            </select>
            <br>
            <label>Tipo de Menopausia</label>
            <select class="form-control" name="tipomeno">
                <option selected value=""> --- </option>
                <option value="NO CONSTA">No consta</option>
                <option value="NATURAL">Natural</option>
                <option value="OVARIECTOMIA">Ovariectomia</option>
                <option value="HISTEROCTOMIA">Histeroctomia</option>
                <option value="AMBAS">Ambas</option>
            </select>
            <label>Menopausia</label>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="radioMeno" value="si">
                <label class="form-check-label">SI</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="radioMeno"  value="no">
                <label class="form-check-label">NO</label>
            </div>
            <input  class="btn btn-primary" type="submit" name="action" value="filtrar"/>
        </form>
        <%            
            if (request.getParameter("error") != null) {

                String error = request.getParameter("error");
                if (error.equals("1")) {
                    out.println("Selecione uno de los campos");
                }
                if (error.equals("2")) {
                    out.println("No se encontro ningun dato");
                }
            }

        %>
    </body>
</html>
