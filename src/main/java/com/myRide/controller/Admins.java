package com.myRide.controller;

import com.myRide.entity.User;
import com.myRide.persistence.GenericDao;
import com.myRide.utilities.SessionUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * The type Admins.
 */
@WebServlet(name = "Admins", urlPatterns = {"/admins"})

public class Admins extends HttpServlet {

    private GenericDao<User> userDAO;

    public void init() {
        userDAO = new GenericDao(User.class);
    }

    /**
     * Handles HTTP GET requests.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        //Put user in session
        SessionUser sessionUser = new SessionUser();
        session.setAttribute("user",sessionUser.getSessionUser(request));

        String action = request.getParameter("action");

        if (action.equals("list") == true) {
            listUser(request, response);
        }else {
            deleteUser(request, response);
        }
    }

    /**
     * Handles HTTP POST requests.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * List all users
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */
    private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        List<User> users = userDAO.getAll();
        request.setAttribute("users", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("users.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Delete selected user
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */
    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        //Get values from request
        int userId = Integer.parseInt(request.getParameter("userid"));

        //Delete user
        userDAO.delete(userDAO.getById(userId));

        //Back to user list page
        response.sendRedirect("admins?action=list");
    }

}

