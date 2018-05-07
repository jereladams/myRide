package com.myRide.controller;

import com.myRide.entity.User;
import com.myRide.entity.Role;
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
import java.util.ArrayList;

/**
 * The type Users.
 */
@WebServlet(name = "Users", urlPatterns = {"/users"})

public class Users extends HttpServlet {

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

        switch (action) {
            case "new":
                showNewForm(request, response);
                break;
            case "insert":
                insertUser(request, response);
                break;
             case "edit":
                showEditForm(request, response);
                break;
            case "update":
                updateUser(request, response);
                break;
            default:
                logoutUser(request, response);
                break;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Show new user form
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("userform.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Show edit user form
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("userform.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Insert user
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */
    private void insertUser(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,IOException {

        //Get values from request
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        //Set properties
        User user = new User(email,password);

        //Add user
        int userId = userDAO.insert(user);

        //Get userId
        user = userDAO.getById(userId);

        //Add to role
        GenericDao<Role> roleDAO = new GenericDao(Role.class);
        Role role = new Role(user,"User",email);
        roleDAO.insert(role);

        //Put user in session
        HttpSession session = request.getSession();
        session.setAttribute("user",user);

        //Set user to logged in
        request.login(email,password);

        //Back to index page
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Update user
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */
    private void updateUser(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,IOException {

        //Get user from session
        HttpSession session = request.getSession();
        User user = (User)(session.getAttribute("user"));

        //Get values from request
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        //Set properties
        user.setEmail(email);
        user.setPassword(password);

        //Save changes
        userDAO.saveOrUpdate(user);

        //Back to index page
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Logout user
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */
    private void logoutUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException {

        //Remove user from session
        HttpSession session = request.getSession();
        session.removeAttribute("user");

        //Logout user
        request.logout();

        //Back to index page
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Validate input
     *
     * @param email  the user email
     * @param password  the user password
     *
     * @return array of error messages
     */
    private ArrayList<String> validateInput(String email, String password) {

        ArrayList<String> errorMessages = new ArrayList();

        //Make sure value was entered.
        if ((email == null) || (email.length() == 0)) {
            errorMessages.add("Email address required.");
        }

        //Check length.
        if (email.length() > 50 ) {
            errorMessages.add("Email value must be less than 50 characters.");
        }

        //Make sure value was entered.
        if ((password == null) || (password.length() == 0)) {
            errorMessages.add("Password required.");
        }

        //Check length.
        if (password.length() < 6) {
            errorMessages.add("Password value must be at least 6 characters.");
        }

        //Check length.
        if (password.length() > 25 ) {
            errorMessages.add("Password value must be less than 25 characters.");
        }

        return errorMessages;
    }
}

