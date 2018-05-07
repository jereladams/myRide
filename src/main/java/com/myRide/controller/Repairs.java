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
import java.util.ArrayList;
import java.util.Set;

/**
 * The type Repairs.
 */
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
    @Override
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
            case "newedit":
                showNewEditForm(request, response);
                break;
            case "currentedit":
                showCurrentEditForm(request, response);
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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * List repairs
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */
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

    /**
     * Show new repair form
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = "insert";

        //Get values from request
        int carId = Integer.parseInt(request.getParameter("carid"));

        //Add carid to request
        request.setAttribute("carid",carId);

        //Add request action to request
        request.setAttribute("action",action);

        //Open repair page
        RequestDispatcher dispatcher = request.getRequestDispatcher("repairform.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Show edit repair form
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */
    private void showNewEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = "edit";

        //Get values from request
        Integer repairId = Integer.parseInt(request.getParameter("repairid"));
        Integer carId = Integer.parseInt(request.getParameter("carid"));

        //Get repair
        Repair repair = repairDAO.getById(repairId);

        //Add repair to request
        request.setAttribute("repairid", repairId);
        request.setAttribute("carid",carId);

        request.setAttribute("servicedate",repair.getServiceDate());
        request.setAttribute("invoicenumber",repair.getInvoiceNumber());
        request.setAttribute("serviceprovider",repair.getServiceProvider());
        request.setAttribute("laborcost",repair.getLaborCost());
        request.setAttribute("currentmileage",repair.getCurrentMileage());
        request.setAttribute("warranty",repair.getWarranty());
        request.setAttribute("serviceperformed",repair.getServicePerformed());
        request.setAttribute("description",repair.getDescription());

        //Add request action to request
        request.setAttribute("action",action);

        //Open repair page
        RequestDispatcher dispatcher = request.getRequestDispatcher("repairform.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Show edit repair form that has validation errors
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */
    private void showCurrentEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = "edit";

        //Get values from request
        Integer repairId = Integer.parseInt(request.getParameter("repairid"));
        Integer carId = Integer.parseInt(request.getParameter("carid"));

        //Add repair to request
        request.setAttribute("repairid", repairId);
        request.setAttribute("carid",carId);

        request.setAttribute("servicedate",request.getParameter("servicedate"));
        request.setAttribute("invoicenumber",request.getParameter("invoicenumber"));
        request.setAttribute("serviceprovider",request.getParameter("serviceprovider"));
        request.setAttribute("laborcost",request.getParameter("laborcost"));
        request.setAttribute("currentmileage",request.getParameter("currentmileage"));
        request.setAttribute("warranty",request.getParameter("warranty"));
        request.setAttribute("serviceperformed",request.getParameter("serviceperformed"));
        request.setAttribute("description",request.getParameter("description"));

        //Add request action to request
        request.setAttribute("action",action);

        //Open repair page
        RequestDispatcher dispatcher = request.getRequestDispatcher("repairform.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Insert repair
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */
    private void insertRepair(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException {

        //Validate input
        ArrayList<String> errorMessages = new ArrayList();
        errorMessages = validateInput(request);

        if (errorMessages.size() == 0) {
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
        }else{
            //Add error messages to session
            HttpSession session = request.getSession();
            session.setAttribute("errorMessages",errorMessages);

            //Add repair to request
            request.setAttribute("repairid", request.getParameter("repairid"));
            request.setAttribute("carid",request.getParameter("carid"));
            request.setAttribute("servicedate",request.getParameter("servicedate"));
            request.setAttribute("invoicenumber",request.getParameter("invoicenumber"));
            request.setAttribute("serviceprovider",request.getParameter("serviceprovider"));
            request.setAttribute("laborcost",request.getParameter("laborcost"));
            request.setAttribute("currentmileage",request.getParameter("currentmileage"));
            request.setAttribute("warranty",request.getParameter("warranty"));
            request.setAttribute("serviceperformed",request.getParameter("serviceperformed"));
            request.setAttribute("description",request.getParameter("description"));

            //Return to new repair page
            RequestDispatcher dispatcher = request.getRequestDispatcher("repairs?action=new");
            dispatcher.forward(request, response);

        }
    }

    /**
     * Edit repair
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */
    private void updateRepair(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException {

        //Validate input
        ArrayList<String> errorMessages = new ArrayList();
        errorMessages = validateInput(request);

        if (errorMessages.size() == 0) {
            //Get values from request
            Integer carId = Integer.parseInt(request.getParameter("carid"));
            Integer repairID = Integer.parseInt(request.getParameter("repairid"));
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
            Repair repair = new Repair(repairID, car, serviceDate, invoiceNumber, serviceProvider, laborCost,
                    currentMileage, warranty, servicePerformed, description);

            //Back to repair list page
            repairDAO.saveOrUpdate(repair);
            response.sendRedirect("repairs?action=list&carid=" + carId);
        }else{
            //Add error messages to session
            HttpSession session = request.getSession();
            session.setAttribute("errorMessages",errorMessages);

            //Add repair to request
            request.setAttribute("repairid", request.getParameter("repairid"));
            request.setAttribute("carid",request.getParameter("carid"));
            request.setAttribute("servicedate",request.getParameter("servicedate"));
            request.setAttribute("invoicenumber",request.getParameter("invoicenumber"));
            request.setAttribute("serviceprovider",request.getParameter("serviceprovider"));
            request.setAttribute("laborcost",request.getParameter("laborcost"));
            request.setAttribute("currentmileage",request.getParameter("currentmileage"));
            request.setAttribute("warranty",request.getParameter("warranty"));
            request.setAttribute("serviceperformed",request.getParameter("serviceperformed"));
            request.setAttribute("description",request.getParameter("description"));

            //Return to edit page
            RequestDispatcher dispatcher = request.getRequestDispatcher("repairs?action=currentedit");
            dispatcher.forward(request, response);

            }
    }

    /**
     * Delete repair
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws IOException      if there is an IO failure
     */
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

    /**
     * Validate input
     *
     * @param request  the HttpServletRequest
     *
     * @return array of error messages
     */
    private ArrayList<String> validateInput(HttpServletRequest request) {

        ArrayList<String> errorMessages = new ArrayList();

        //Invoice Number
        if(request.getParameter("invoicenumber").length() > 25) {
            errorMessages.add("Invoice # must be less than 25 characters.");
        }

        //Service Provider
        if(request.getParameter("serviceprovider").length() > 45) {
            errorMessages.add("Service Provider must be less than 45 characters.");
        }

        try {
            //Parse labor cost
            Double laborCost = Double.parseDouble(request.getParameter("laborcost"));

        } catch (NumberFormatException nfe) {
            errorMessages.add("Invalid Cost format. examole: 10.00");
        }

        try {
            //Parse mileage
            Integer currentMileage = Integer.parseInt(request.getParameter("currentmileage"));

        } catch (NumberFormatException nfe) {
            errorMessages.add("Invalid Mileage format. Only numbers allowed.");
        }

        //Warranty
        if(request.getParameter("warranty").length() > 45) {
            errorMessages.add("Warranty must be less than 45 characters.");
        }

        //Service Performed
        if(request.getParameter("serviceperformed").length() > 100) {
            errorMessages.add("Invoice # must be less than 100 characters.");
        }

        //Description
        if(request.getParameter("description").length() > 500) {
            errorMessages.add("Description must be less than 500 characters.");
        }

        return errorMessages;
    }

}



