package com.myRide.controller;

import com.myRide.entity.Part;
import com.myRide.entity.Repair;
import com.myRide.persistence.GenericDao;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Parts", urlPatterns = {"/parts"})

public class Parts extends HttpServlet {

    private GenericDao<Part> partDAO;

    public void init() {
        partDAO = new GenericDao(Part.class);
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

        String action = request.getParameter("action");

        if (action == null){
            action = "list";
        }

        switch (action) {
            case "new":
                showNewForm(request, response);
                break;
            case "insert":
                insertPart(request, response);
                break;
            case "delete":
                deletePart(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "update":
                updatePart(request, response);
                break;
            default:
                listPart(request, response);
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
     * List parts
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */
    private void listPart(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        List<Part> parts = partDAO.getAll();
        request.setAttribute("parts", parts);

        GenericDao<Repair>  repairDao = new GenericDao(Repair.class);
        int repairId = Integer.parseInt(request.getParameter("repairid"));
        Repair repair = repairDao.getById(repairId);

        request.setAttribute("repair", repair);

        RequestDispatcher dispatcher = request.getRequestDispatcher("parts.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Show add part form
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("partform.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Show edit part form
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int partID = Integer.parseInt(request.getParameter("partID"));
        Part existingPart = partDAO.getById(partID) ;
        RequestDispatcher dispatcher = request.getRequestDispatcher("PartForm.jsp");
        request.setAttribute("part", existingPart);
        dispatcher.forward(request, response);
    }

    /**
     * Insert new part
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws IOException      if there is an IO failure
     */
    private void insertPart(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int repairID = Integer.parseInt(request.getParameter("repairId"));
        String partName = request.getParameter("partName");
        String manufacturer = request.getParameter("manufacturer");
        String partNumber = request.getParameter("partNumber");
        String warranty = request.getParameter("warranty");
        String supplier = request.getParameter("supplier");
        Double price = Double.parseDouble(request.getParameter("servicePerformed"));
        String description = request.getParameter("description");

        GenericDao<Repair>  repairDao = new GenericDao(Repair.class);
        Repair repair = repairDao.getById(repairID);

        Part newPart = new Part(repair,partName,manufacturer,partNumber,warranty,supplier,price,
                description);

        partDAO.insert(newPart) ;

        response.sendRedirect("parts");
    }

    /**
     * Update part
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws IOException      if there is an IO failure
     */
    private void updatePart(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int repairID = Integer.parseInt(request.getParameter("repairID"));
        int partID = Integer.parseInt(request.getParameter("partID"));
        String partName = request.getParameter("partName");
        String manufacturer = request.getParameter("manufacturer");
        String partNumber = request.getParameter("partNumber");
        String warranty = request.getParameter("warranty");
        String supplier = request.getParameter("supplier");
        Double price = Double.parseDouble(request.getParameter("servicePerformed"));
        String description = request.getParameter("description");

        GenericDao<Repair> repairDAO = new GenericDao(Repair.class);
        Repair repair = repairDAO.getById(repairID);

        Part part = new Part(partID,repair,partName,manufacturer,partNumber,warranty,supplier,price,
                description);

         partDAO.saveOrUpdate(part) ;
        response.sendRedirect("parts");
    }

    /**
     * Delete part
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws IOException      if there is an IO failure
     */
    private void deletePart(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html");
        int partID = Integer.parseInt(request.getParameter("partid"));
        partDAO.delete(partDAO.getById(partID));
        response.sendRedirect("parts?");
    }
}

