package com.myRide.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.webServices.recalls.RecallItem;
import com.webServices.recalls.RecallResults;

import com.webServices.vin.VinItem;
import com.webServices.vin.VinResults;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Vin.
 */
@WebServlet(name = "Vin", urlPatterns = {"/vin"})

public class Vin extends HttpServlet {

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

        //Get values from request
        String vin = request.getParameter("vin");
        String carid = request.getParameter("carid");

        //Validate input
        ArrayList<String> errorMessages = new ArrayList();
        errorMessages = validateInput(vin);

        if (errorMessages.size() == 0) {

            //Decode VIN
            VinResults vinResults = getVinResults(vin);

            if (vinResults.getCount() > 0) {

                VinItem vinItem = vinResults.getResults().get(0);

                //Get year,make,model,trim from webservice
                String year = vinItem.getModelYear();
                String make = vinItem.getMake();
                String model = vinItem.getModel();
                String trim = vinItem.getTrim();

                //Car found
                if (year != "" || make != "" || model != "") {
                    request.setAttribute("vin", vin);
                    request.setAttribute("year", year);
                    request.setAttribute("make", make);
                    request.setAttribute("model", model);
                    request.setAttribute("trim", trim);
                    request.setAttribute("carid",carid);

                    //Get recalls from webservice
                    RecallResults recallResults = getRecallResults(year,make,model);
                   List<RecallItem> recallList = recallResults.getResults();

                    request.setAttribute("recalls", recallList);

                    //Return to index page
                    RequestDispatcher dispatcher = request.getRequestDispatcher("results.jsp");
                    dispatcher.forward(request, response);
                }else{
                    //Add error messages to request
                    errorMessages.add("No results.");
                    request.setAttribute("errorMessages", errorMessages);

                    //Return to index page
                    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                    dispatcher.forward(request, response);
                }
            }else{
                //Add error messages to request
                errorMessages.add("No results.");
                request.setAttribute("errorMessages", errorMessages);

                //Return to index page
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            }
        }else{
            //Add error messages to request
            request.setAttribute("errorMessages", errorMessages);

            //Return to index page
            RequestDispatcher dispatcher = request.getRequestDispatcher("/");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Decodes the VIN
     *
     * @param vin  the Vehicle ID Number
     *
     * @return the Vin WebService Results
     */
    private VinResults getVinResults(String vin) {
        Client client = ClientBuilder.newClient();

        //Get url from web.xml
        ServletContext context = getServletContext();
        String url = context.getInitParameter("vinurl") + vin + context.getInitParameter("format");

        WebTarget target = client.target(url);

        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES , false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            // Convert JSON to Object
            VinResults vinResults = mapper.readValue(response, VinResults.class);
            return vinResults;

        } catch (JsonGenerationException e) {
            e.printStackTrace();
            return null;
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Search recalls
     *
     * @param year  the vehicle year
     * @param make  the vehicle make
     * @param model  the vehicle model
     *
     * @return the Recall WebService Results
     */
     private RecallResults getRecallResults(String year, String make, String model) {
        Client client = ClientBuilder.newClient();

       //Get url from web.xml
       ServletContext context = getServletContext();
       String url = context.getInitParameter("recallurl") + year +
               "/make/" + make + "/model/" + model + context.getInitParameter("format");

        WebTarget target = client.target(url);

        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES , false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {

            // Convert JSON to Object
            RecallResults recallResults = mapper.readValue(response, RecallResults.class);

            return recallResults;

        } catch (JsonGenerationException e) {
            e.printStackTrace();
            return null;
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Validate input
     *
     * @param vin the vehicle id number
     *
     * @return array of error messages
     */
    private ArrayList<String> validateInput(String vin) {

        ArrayList<String> errorMessages = new ArrayList();

        //Make sure value was entered.
        if ((vin == null) || (vin.length() != 17)) {
            errorMessages.add("VIN value must be 17 characters.");
        }

        return errorMessages;
    }
}


