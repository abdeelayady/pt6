/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.persist.PacienteDAO;
import models.Paciente;

/**
 *
 * @author alumne
 */
@WebServlet(name = "Controller", urlPatterns = {"/osteo"})
public class Controller extends HttpServlet {

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

        if (request.getParameter("action") != null) {
            String action = request.getParameter("action");
            switch (action) {
                case "getvalues":
                    getValues(request, response);
                    break;
                case "AddPaciente":
                    addPacient(request, response);
                    break;
                case "filtrar":
                    filtrar(request, response);
                    break;

            }

        } else {
            response.sendRedirect("index.jsp");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void getValues(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        //calcula el path para legar a WEB-INF
        String ruta = getServletContext().getRealPath("/WEB-INF/resources");
        PacienteDAO odao = new PacienteDAO(ruta);

        List<Paciente> list = odao.findAll(2);
//        List<String> list_strings = new ArrayList();
//        for (Paciente o : list) {
//            list_strings.add(o.toString());
//        }

        //recorrer lista y enviarlo al jsp
        //crear una variable de sesion
        request.setAttribute("list", list);
        RequestDispatcher rd = request.getRequestDispatcher("getValues.jsp");
        rd.forward(request, response);
    }
    /**
     * 
     * @param request
     * @param response
     * @throws IOException 
     */
    private void addPacient(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //calcula el path para legar a WEB-INF
        String ruta = getServletContext().getRealPath("/WEB-INF/resources");
        PacienteDAO pd = new PacienteDAO(ruta);

        int edad = parseInt(request.getParameter("edad"));
        String grupEdat = request.getParameter("grupoEdad");
        int pes = parseInt(request.getParameter("peso"));
        int altura = parseInt(request.getParameter("altura"));
        String clasificacion = request.getParameter("clasificacion");
        int menarquia = parseInt(request.getParameter("menarquia"));
        String radioMeno = request.getParameter("radioMeno");
        String tipoMeno = request.getParameter("tipoMeno");
        //calculamos imc
        double imc = (pes / altura) ^ 2;
        //creo el objeto paciente nuevo
        int error = 0;
        if (edad == 0 && grupEdat.equals("") && pes == 0 && altura == 0 && clasificacion.equals("")
                && menarquia == 0 && radioMeno.equals("") && tipoMeno.equals("")) {
            response.sendRedirect("addPaciente.jsp?error=1");
        } else {
            Paciente p = new Paciente(edad, grupEdat, pes, altura, imc, clasificacion, menarquia, radioMeno, tipoMeno);

            if (pd.insert(p) != 0) {
                response.sendRedirect("addPaciente.jsp?error=3");

            } else {
                response.sendRedirect("addPaciente.jsp?error=2");
            }

        }

    }
    /**
     * 
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException 
     */
    private void filtrar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String clasificacion = request.getParameter("clasificacion");
        String menopausia = request.getParameter("radioMeno");
        String tipoMeno = request.getParameter("tipomeno");

        String ruta = getServletContext().getRealPath("/WEB-INF/resources");
        PacienteDAO pDao = new PacienteDAO(ruta);

        if ((request.getParameter("radioMeno") == null) && "".equals(request.getParameter("clasificacion")) && "".equals(request.getParameter("tipomeno"))) {
            response.sendRedirect("filtrar.jsp?error=1");
        } else {
            if (menopausia != null && !"".equals(clasificacion) && !"".equals(tipoMeno)) {

                List<Paciente> filter = pDao.filterAll(menopausia, clasificacion, tipoMeno);

                if (filter.isEmpty()) {
                    response.sendRedirect("filtrar.jsp?error=2");
                } else {
                    request.setAttribute("filter", filter);
                    //---------------------------------------------
                    HttpSession session = request.getSession();
                    session.setAttribute("listapdf", filter);
                    RequestDispatcher rd = request.getRequestDispatcher("result_filtro.jsp");
                    rd.forward(request, response);
                }
            } else if ((menopausia != null && !"".equals(clasificacion))) {

                List<Paciente> listTypes = pDao.filterMenoClasi(menopausia, clasificacion);

                if (listTypes.isEmpty()) {
                    response.sendRedirect("filtrar.jsp?error=2");
                } else {
                    request.setAttribute("filter", listTypes);
                    //---------------------------------------------
                    HttpSession session = request.getSession();
                    session.setAttribute("listapdf", listTypes);
                    RequestDispatcher rd = request.getRequestDispatcher("result_filtro.jsp");
                    rd.forward(request, response);
                }

            } else if ((menopausia != null && !"".equals(tipoMeno))) {

                List<Paciente> listTypes = pDao.filterMenoYtipo(menopausia, tipoMeno);

                if (listTypes.isEmpty()) {
                    response.sendRedirect("filtrar.jsp?error=2");
                } else {
                    request.setAttribute("filter", listTypes);
                    //---------------------------------------------
                    HttpSession session = request.getSession();
                    session.setAttribute("listapdf", listTypes);
                    RequestDispatcher rd = request.getRequestDispatcher("result_filtro.jsp");
                    rd.forward(request, response);
                }
            } else if ((!"".equals(tipoMeno) && !"".equals(clasificacion))) {
                List<Paciente> listTypes = pDao.filterClaYtipo(clasificacion, tipoMeno);

                if (listTypes.isEmpty()) {
                    response.sendRedirect("filtrar.jsp?error=2");
                } else {
                    request.setAttribute("filter", listTypes);
                    HttpSession session = request.getSession();
                    session.setAttribute("listapdf", listTypes);
                    RequestDispatcher rd = request.getRequestDispatcher("result_filtro.jsp");
                    rd.forward(request, response);
                }
            } else if (menopausia != null) {

                List<Paciente> lisMenopausia = pDao.filterOne(null, menopausia, null);

                if (lisMenopausia.isEmpty()) {
                    response.sendRedirect("filtrar.jsp?error=2");
                } else {
                    request.setAttribute("filter", lisMenopausia);
                    HttpSession session = request.getSession();
                    session.setAttribute("listapdf", lisMenopausia);
                    RequestDispatcher rd = request.getRequestDispatcher("result_filtro.jsp");
                    rd.forward(request, response);
                }
            } else if (!"".equals(clasificacion)) {

                List<Paciente> lisClasificacion = pDao.filterOne(clasificacion, null, null);

                if (lisClasificacion.isEmpty()) {
                    response.sendRedirect("filtrar.jsp?error=2");
                } else {
                    request.setAttribute("filter", lisClasificacion);
                    HttpSession session = request.getSession();
                    session.setAttribute("listapdf", lisClasificacion);
                    RequestDispatcher rd = request.getRequestDispatcher("result_filtro.jsp");
                    rd.forward(request, response);
                }
            } else if (!"".equals(tipoMeno)) {

                List<Paciente> listTipoMeno = pDao.filterOne(null, null, tipoMeno);

                if (listTipoMeno.isEmpty()) {
                    response.sendRedirect("filtrar.jsp?error=2");
                } else {
                    request.setAttribute("filter", listTipoMeno);
                    HttpSession session = request.getSession();
                    session.setAttribute("listapdf", listTipoMeno);
                    RequestDispatcher rd = request.getRequestDispatcher("result_filtro.jsp");
                    rd.forward(request, response);
                }
            }
        }
    }

}
