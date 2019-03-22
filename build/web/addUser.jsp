<%-- 
    Document   : addUser
    Created on : 20-mar-2019, 16:25:23
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
            <form class="px-4 py-3" method="post" action="user">
                <div class="form-group">
                    <label>Nombre de Usuario</label>
                    <input type="text" class="form-control" placeholder="usuario" name="usuario">
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input type="password" class="form-control" placeholder="password" name="password1">
                </div>
                <div class="form-group">
                    <label>Repeat Password</label>
                    <input type="password" class="form-control" placeholder="password" name="password2">
                </div>
                <br>
                <input  class="btn btn-primary" type="submit" name="action" value="add"/>
                <a href="index.jsp" class="btn btn-primary">Back</a>
            </form>
            <div class="dropdown-divider"></div>
        </div>
        <%            if (request.getParameter("error") != null) {

                String error = request.getParameter("error");
                if (error.equals("1")) {
                    out.println("Complete todos los campos");
                }
                if (error.equals("2")) {
                    out.println("Las contraseñas no son iguales");
                }
                if (error.equals("3")) {
                    out.println("El nombre de usuario ya existe");
                }
                if (error.equals("4")) {
                    out.println("Usuario Creado Correctamente");
                }
                if (error.equals("5")) {
                    out.println("Ha ocurrido un error usuario no insertado");
                }

            }
        %>
    </body>
</html>