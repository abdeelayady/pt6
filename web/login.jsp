<%-- 
    Document   : login
    Created on : 05-feb-2019, 17:21:16
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
        <header><%@include file="menu.jsp" %></header>
        <form class="form-group" method="post" action="user" style="width: 20%">
            Username: <input class="form-control" type="text" name="username" required/>
            <br/>
            Password: <input class="form-control" type="password" name="password" required/>
            <br/>
            <input type="submit" name="action" value="Validate"/>
                        
        </form>
        <%
            
           if(request.getParameter("error")!=null){
              //out.println("Usuario y/o contraseña incorrectas");
   
               String error=request.getParameter("error");
               if(error.equals("1")) out.println("Usuario y/o contraseña incorrectas");
           }            
        %>
    </body>
</html>
