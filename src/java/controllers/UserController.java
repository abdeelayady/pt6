/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.persist.PacienteDAO;
import model.persist.UsersDAO;
import models.Paciente;
import models.User;


/**
 *
 * @author alumne
 */
@WebServlet(name = "UserController", urlPatterns = {"/user"})
public class UserController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ruta = getServletContext().getRealPath("/WEB-INF");        
        
        if (request.getParameter("action") != null) {
            String action = request.getParameter("action");
            switch (action) {
                case "Validate":
                    login(request, response);
                    break;
                case "add":
                    addUser(request, response);
                    break;

            }

        } else {
            response.sendRedirect("index.jsp");
        }

    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //calcula el path para legar a WEB-INF
        String ruta = getServletContext().getRealPath("/WEB-INF/resources");
        UsersDAO udao = new UsersDAO(ruta);
        PacienteDAO pd = new PacienteDAO(ruta);
        //recogemos desde el formulario
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        User u = new User(username, password);
        String result = udao.findOne(u);
        if (result!=null || !"".equals(result)) {
            //crear una variable de sesion
            HttpSession session = request.getSession();
            session.setAttribute("name", username);
            HttpSession sessionRol = request.getSession();
            session.setAttribute("rol", result);
            List<Paciente> all = pd.findAll(1);
                    session.setAttribute("list", all);
            response.sendRedirect("page.jsp");
            

        } else {
            response.sendRedirect("login.jsp?error=1");

        }
    }

 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException  {
        
        String username = request.getParameter("usuario");
        String password = request.getParameter("password1");
        String password2 = request.getParameter("password2");

        if (password.equals(password2)) {

            //calcula el path para legar a WEB-INF
        String ruta = getServletContext().getRealPath("/WEB-INF/resources");
        UsersDAO udao = new UsersDAO(ruta);

            boolean res = udao.findName(username);

            if (res) {
                response.sendRedirect("addUser.jsp?error=3");
            } else {
                if (udao.addUser(username, password) == 1) {
                    response.sendRedirect("addUser.jsp?error=4");
                } else {
                    response.sendRedirect("addUser.jsp?error=5");
                }
            }

        } else {

            response.sendRedirect("addUser.jsp?error=2");
        }

    }
 }


