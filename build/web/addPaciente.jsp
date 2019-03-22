<%-- 
    Document   : addPaciente
    Created on : 13-mar-2019, 17:40:10
    Author     : abde
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>Agregar Usuario</title>
    </head>
    <body>
        <%@include file="menu.jsp"%>
        <h1>Afegir Usuari</h1>
        <div class="container" style="width: 29%;position: relative;right: 37%;">
            <form class="px-4 py-3" method="post" action="osteo">
                <div class="form-group">
                    <label>Edad</label>
                    <input type="number" class="form-control" placeholder="edad" name="edad">
                </div>
                <div class="form-group">
                    <label>Grupo Edad</label>
                    <select class="form-control" name="grupoEdad">
                        <option selected value=""> --- </option>
                        <option  value="45-49"> 45 - 49 </option>
                        <option  value="55-59"> 55 - 59 </option>
                        <option  value="50-54"> 50 - 54 </option>
                    </select>
                </div>
                <div class="form-group">
                    <label>Peso</label>
                    <input type="number" class="form-control" placeholder="Kg" name="peso">
                </div>
                <div class="form-group">
                    <label>Altura</label>
                    <input type="number" class="form-control" placeholder="cm" name="altura">
                </div>
                <div class="form-group">
                    <label>Grupo Edad</label>
                    <select class="form-control" name="clasificacion">
                        <option selected value=""> --- </option>
                        <option value="osteopenia">Osteopenia</option>
                        <option value="normal">Normal</option>
                        <option value="osteoporosi">Osteoporosis</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>Menarquia</label>
                    <input type="number" class="form-control" placeholder="cm" name="menarquia">
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="radioMeno"  value="no">
                    <label class="form-check-label">NO</label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="radioMeno"  value="si">
                    <label class="form-check-label">SI</label>
                </div>
                <label>Tipo de Menopausia</label>
                <select class="form-control" name="tipoMeno">
                    <option selected value=""> --- </option>
                    <option value="NO CONSTA">No consta</option>
                    <option value="NATURAL">Natural</option>
                    <option value="OVARIECTOMIA">Ovariectomia</option>
                    <option value="HISTEROCTOMIA">Histeroctomia</option>
                    <option value="AMBAS">Ambas</option>
                </select>
                <br>
                <input  class="btn btn-primary" type="submit" name="action" value="AddPaciente"/>
            </form>
            <div class="dropdown-divider"></div>
        </div>
        <%            
            if (request.getParameter("error") != null) {

                String error = request.getParameter("error");
                if (error.equals("1")) {
                    out.println("Complete todos los campos");
                }
                if (error.equals("2")) {
                    out.println("Error al ingresar el paciente");
                }
                if (error.equals("3")) {
                    out.println("Usuario Creado Correctamente");
                }

            }


        %>
    </body>
</html>