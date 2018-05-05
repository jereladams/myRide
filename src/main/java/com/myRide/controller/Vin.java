package com.myRide.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.webServices.recalls.RecallItem;
import com.webServices.recalls.RecallResults;

import com.webServices.vin.VinItem;
import com.webServices.vin.VinResults;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
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

@WebServlet(name = "Vin", urlPatterns = {"/vin"})

public class Vin extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

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

        ArrayList<String> errorMessages = new ArrayList();

        String vin = request.getParameter("vin");
        String carid = request.getParameter("carid");

        errorMessages = validateInput(request);

        if (errorMessages.size() == 0) {

            VinResults vinResults = getVinResults(vin);

            if (vinResults.getCount() > 0) {
                VinItem vinItem = vinResults.getResults().get(0);

                //Get year,make,model,trim from webservice
                String year = vinItem.getModelYear();
                String make = vinItem.getMake();
                String model = vinItem.getModel();
                String trim = vinItem.getTrim();

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
                    errorMessages.add("No results.");
                    //Add error messages to request
                    request.setAttribute("errorMessages", errorMessages);

                    //Return to index page
                    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                    dispatcher.forward(request, response);
                }
            }else{
                errorMessages.add("No results.");
                //Add error messages to request
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

    private VinResults getVinResults(String vin) {
        Client client = ClientBuilder.newClient();

        String url = "https://vpic.nhtsa.dot.gov/api/vehicles/decodevinvalues/" + vin + "?format=json";

        System.out.println(url);

        WebTarget target = client.target(url);

        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES , false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {

            // Convert JSON to Object
            VinResults vinResults = mapper.readValue(response, VinResults.class);

            System.out.println(vinResults.toString());

            System.out.println(vinResults.getResults().toString());

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

     private RecallResults getRecallResults(String year, String make, String model) {
        Client client = ClientBuilder.newClient();

        String url = "https://one.nhtsa.gov/webapi/api/Recalls/vehicle/modelyear/" + year +
                        "/make/" + make + "/model/" + model + "?format=json";

        System.out.println(url);

        WebTarget target = client.target(url);

        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES , false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {

            // Convert JSON to Object
            RecallResults recallResults = mapper.readValue(response, RecallResults.class);

            System.out.println(recallResults.toString());

            System.out.println(recallResults.getResults().toString());

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

    private ArrayList<String> validateInput(HttpServletRequest request) {

        ArrayList<String> errorMessages = new ArrayList();
        String vin = request.getParameter("vin");

        //Make sure value was entered.
        if ((vin == null) || (vin.length() != 17)) {
            errorMessages.add("VIN value must be 17 characters.");
        }

        return errorMessages;
    }
}


