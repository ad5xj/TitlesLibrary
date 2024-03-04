package org.ad5xj.Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.ad5xj.mariadb.User;
import org.ad5xj.mariadb.UserImplDAO;

import java.io.IOException;
import java.io.PrintWriter;

public class RegisterController  extends HttpServlet
{
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html; charset=UTF-8");
        try (PrintWriter out = response.getWriter())
        {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegisterServlet</title>");
            out.println("</head>");
            out.println("<body>");


            String login = request.getParameter("login");
            String privlvl = request.getParameter("privlvl");
            String name = request.getParameter("username");
            String passwd = request.getParameter("password");
            //make user object
            User user = new User();
            user.setLogin(login);
            user.setPrivLvl(Integer.getInteger(privlvl));
            user.setName(name);
            user.setPasswd(passwd);
            UserImplDAO userobj = new UserImplDAO();
            boolean ok = userobj.add(user);

            if (ok)
            {
                response.sendRedirect("index.jsp");
            }
            else
            {
                String errorMessage = "User Available";
                HttpSession regSession = request.getSession();
                regSession.setAttribute("RegError", errorMessage);
                response.sendRedirect("registration.jsp");
            }

            out.println("</body>");
            out.println("</html>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() { return "Short description"; }
}