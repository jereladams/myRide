package com.myRide.controller;

import com.myRide.entity.Car;
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
import java.util.Set;

@WebServlet(name = "Cars", urlPatterns = {"/cars"})

public class Cars extends HttpServlet {
        
    private GenericDao<Car> carDAO;

    public void init() {
        carDAO = new GenericDao(Car.class);
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

        //Get request action
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "insert":
                    insertCar(request, response);
                    break;
                case "delete":
                    deleteCar(request, response);
                    break;
                default:
                    listCar(request, response);
                    break;
            }
        }else{
            //Back to index page page
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
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
     * List cars
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */
    private void listCar(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        //Get user from session
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        //Get new user
        GenericDao<User>  userDao = new GenericDao(User.class);
        User newUser = userDao.getById(user.getId());

        //Reload cars
        Set<Car> cars = newUser.getCars();

        //Add new user to session
        session.setAttribute("user",newUser);

        request.setAttribute("cars", cars);
        RequestDispatcher dispatcher = request.getRequestDispatcher("cars.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Insert new car
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
      * @throws IOException      if there is an IO failure
     */
    private void insertCar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        //Get user from session
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        //Get values from request
        String year = request.getParameter("year");
        String make = request.getParameter("make");
        String model = request.getParameter("model");
        String vin = request.getParameter("vin");

        //Add car
        Car newCar = new Car(user,year,make,model,vin);
        carDAO.insert(newCar) ;

        //Back to car list page
        response.sendRedirect("cars?action=list");
    }

    /**
     * Delete car
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws IOException      if there is an IO failure
     */
    private void deleteCar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        //Get values from request
        int carID = Integer.parseInt(request.getParameter("carid"));

        //Delete car
        carDAO.delete(carDAO.getById(carID));

        //Back to cars list page
        response.sendRedirect("cars?action=list");
    }
 }


