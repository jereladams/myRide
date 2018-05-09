package com.myRide.persistence;

import com.myRide.entity.Car;
import com.myRide.entity.Repair;
import com.myRide.testUtils.Database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RepairTest {

    GenericDao<Repair>  repairDao;

    /**
     * Creating the dao.
     */
    @BeforeEach
    void setUp() {

        repairDao = new GenericDao(Repair.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Verifies gets all repairs successfully.
     */
    @Test
    void getAllRepairsSuccess() {
        List<Repair> repairs = repairDao.getAll();
        assertNotNull(repairs);
        assertEquals(4, repairs.size());
    }

    /**
     * Verifies a repair is returned correctly based on id search
     */
    @Test
    void getByIdSuccess() {
        //Get Car
        GenericDao<Car>  carDao = new GenericDao(Car.class);
        Car car = carDao.getById(1);

        LocalDate localDate = LocalDate.parse("2018-01-01");
        Double laborCost = 300.00;
        int currentMileage = 45000;

        //Create Test Repair
        Repair testRepair = new Repair(1,car,localDate,"123-456","Midas",laborCost,currentMileage,"1 year labor","Exhaust","Replaced muffler and tailpipe");

        //Get Existing Repair
        Repair retrievedRepair = repairDao.getById(1);
        assertNotNull(retrievedRepair);

        //Compare Cars
        assertEquals(testRepair,retrievedRepair);
    }

    /**
     * Verify successful insert of a repair
     */
    @Test
    void insertSuccess() {
        int insertedRepairId;

        //Get Car
        GenericDao<Car>  carDao = new GenericDao(Car.class);
        Car car = carDao.getById(2);

        LocalDate localDate = LocalDate.now();
        Double laborCost = 100.00;
        int currentMileage = 50000;

        //Create Inserted Repair
        //Create Test Repair
        Repair insertedRepair = new Repair(car,localDate,"123-456","Midas",laborCost,currentMileage,"5 years parts and labor","Brakes","Replaced rotors and pads" );

        //Save Inserted Repair
        insertedRepairId = repairDao.insert(insertedRepair);

        //Get Saved Repair
        Repair retrievedRepair = repairDao.getById(insertedRepairId);
        assertNotNull(retrievedRepair);

        //Compare Cars
        assertEquals(insertedRepair,retrievedRepair);
    }

    /**
     * Verifies a repair is returned correctly based on service provider search
     */
    @Test
    void getByPropertyExactSuccess() {
        List<Repair> repairList = repairDao.getByPropertyEqual("serviceProvider","Midas") ;
        assertEquals(1, repairList.size());
    }

    /**
     * Verifies a repair is returned correctly based on service provider search
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<Repair> repairList = repairDao.getByPropertyLike("serviceProvider","Mi") ;
        assertEquals(1, repairList.size());
    }

    /**
     * Verify successful delete of repair and parts
     *
     */
    @Test
    void deleteSuccess() {
        repairDao.delete(repairDao.getById(1));
        assertNull(repairDao.getById(1));
    }

    /**
     * Verify successful update of repair
     */
    @Test
    void updateSuccess() {
        String invoiceNumber = "111-222-333";
        Repair repairToUpdate = repairDao.getById(1);
        repairToUpdate.setInvoiceNumber(invoiceNumber);
        repairDao.saveOrUpdate(repairToUpdate);
        Repair retrievedRepair = repairDao.getById(1);
        assertEquals(repairToUpdate, retrievedRepair);
    }

}


