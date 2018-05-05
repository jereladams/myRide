package com.myRide.controller;

import com.myRide.entity.Car;
import com.myRide.entity.Repair;
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
import java.time.LocalDate;
import java.util.Set;

@WebServlet(name = "Repairs", urlPatterns = {"/repairs"})

public class Repairs extends HttpServlet {

    private GenericDao<Repair> repairDAO;

    public void init() {
        repairDAO = new GenericDao(Repair.class);
    }

    /**
     * Handles HTTP GET requests.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        //Put user in session
        SessionUser sessionUser = new SessionUser();
        session.setAttribute("user",sessionUser.getSessionUser(request));

        String action = request.getParameter("action");

        if (action == null){
            action = "list";
        }

        switch (action) {
            case "new":
                showNewForm(request, response);
                break;
            case "insert":
                insertRepair(request, response);
                break;
            case "delete":
                deleteRepair(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "update":
                updateRepair(request, response);
                break;
            default:
                listRepair(request, response);
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

    private void listRepair(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        //Get values from request
        Integer carId = Integer.parseInt(request.getParameter("carid"));

        //Get car
        GenericDao<Car> carDao = new GenericDao(Car.class);
        Car car = carDao.getById(carId);

        //Get repairs
        Set<Repair> repairs = car.getRepairs();

        //Add car to request
        request.setAttribute("carid", car.getId());
        request.setAttribute("year", car.getYear());
        request.setAttribute("make", car.getMake());
        request.setAttribute("model", car.getModel());
        request.setAttribute("vin", car.getVin());

        //Add repairs to request
        request.setAttribute("repairs", repairs);

        //Open repair list page
        RequestDispatcher dispatcher = request.getRequestDispatcher("repairs.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Get values from request
        int carId = Integer.parseInt(request.getParameter("carid"));

        //Add carid to request
        request.setAttribute("carid",carId);

        //Open repair page
        RequestDispatcher dispatcher = request.getRequestDispatcher("repairform.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Get values from request
        int repairId = Integer.parseInt(request.getParameter("repairid"));

        //Get repair
        Repair repair = repairDAO.getById(repairId);

        //Add repair to request
        request.setAttribute("repair", repair);

        //Open repair page
        RequestDispatcher dispatcher = request.getRequestDispatcher("repairform.jsp");
        dispatcher.forward(request, response);
    }

    private void insertRepair(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        //Get values from request
        int carId = Integer.parseInt(request.getParameter("carid"));
        LocalDate serviceDate = LocalDate.parse(request.getParameter("servicedate"));
        String invoiceNumber = request.getParameter("invoicenumber");
        String serviceProvider = request.getParameter("serviceprovider");
        Double laborCost = Double.parseDouble(request.getParameter("laborcost"));
        int currentMileage = Integer.parseInt(request.getParameter("currentmileage"));
        String warranty = request.getParameter("warranty");
        String servicePerformed = request.getParameter("serviceperformed");
        String description = request.getParameter("description");

        //Get car
        GenericDao<Car>  carDao = new GenericDao(Car.class);
        Car car = carDao.getById(carId);

        //Add repair
        Repair newRepair = new Repair(car,serviceDate,invoiceNumber,serviceProvider,laborCost,
                currentMileage,warranty,servicePerformed,description);
        repairDAO.insert(newRepair) ;

        //Back to repair list page
        response.sendRedirect("repairs?action=list&carid=" + carId);
    }

    private void updateRepair(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        //Get values from request
        int carId = Integer.parseInt(request.getParameter("carid"));
        int repairID = Integer.parseInt(request.getParameter("repairid"));
        LocalDate serviceDate = LocalDate.parse(request.getParameter("servicedate"));
        String invoiceNumber = request.getParameter("invoicenumber");
        String serviceProvider = request.getParameter("serviceprovider");
        Double laborCost = Double.parseDouble(request.getParameter("laborcost"));
        Integer currentMileage = Integer.parseInt(request.getParameter("currentmileage"));
        String warranty = request.getParameter("warranty");
        String servicePerformed = request.getParameter("serviceperformed");
        String description = request.getParameter("description");

        //Get car
        GenericDao<Car> carDao = new GenericDao(Car.class);
        Car car = carDao.getById(carId);

        //Add repair
        Repair repair = new Repair(repairID,car,serviceDate,invoiceNumber,serviceProvider,laborCost,
                currentMileage,warranty,servicePerformed,description);

        //Back to repair list page
        repairDAO.saveOrUpdate(repair) ;
        response.sendRedirect("repairs?action=list&carid=" + carId);
    }

    private void deleteRepair(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        //Get values from request
        int repairID = Integer.parseInt(request.getParameter("repairid"));
        int carId = Integer.parseInt(request.getParameter("carid"));

        //Delete repair
        repairDAO.delete(repairDAO.getById(repairID));

        //Back to repair list page
        response.sendRedirect("repairs?carid=" + carId);
    }

}



