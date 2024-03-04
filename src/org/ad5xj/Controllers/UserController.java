package org.ad5xj.Controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ad5xj.mariadb.User;
import org.ad5xj.mariadb.UserImplDAO;

import java.io.IOException;

/**
 * @email Ramesh Fadatare
 */

@WebServlet("/register")
public class UserController extends HttpServlet {
    private UserImplDAO userobj;

    public void init() { userobj = new UserImplDAO(); }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        register(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.sendRedirect("registration.jsp");
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        int    privlvl = Integer.getInteger(request.getParameter("privLvl"));
        String login = request.getParameter("firstName");
        String userName = request.getParameter("lastName");
        String userPasswd = request.getParameter("username");

        User user = new User();
        user.setPrivLvl(privlvl);
        user.setLogin(login);
        user.setName(userName);
        user.setPasswd(userPasswd);

        userobj.add(user);

        RequestDispatcher dispatcher = request.getRequestDispatcher("register-success.jsp");
        dispatcher.forward(request, response);
    }
}