package com.myRide.persistence;

import com.myRide.entity.*;
import com.myRide.testUtils.Database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CarTest {

    GenericDao<Car>  carDao;

    /**
     * Creating the dao.
     */
    @BeforeEach
    void setUp() {

        carDao = new GenericDao(Car.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Verifies gets all cars successfully.
     */
    @Test
    void getAllCarsSuccess() {
        List<Car> cars = carDao.getAll();
        assertNotNull(cars);
        assertEquals(1, cars.size());
    }

    /**
     * Verifies a car is returned correctly based on id search
     */
    @Test
    void getByIdSuccess() {
        //Get User
        GenericDao<User>  userDao = new GenericDao(User.class);
        User user = userDao.getById(2);

        //Create Test Car
        Car testCar = new Car(1,user,"2016","Jeep","Wrangler","1C4BJWFG2GL133333");

        //Ignore Create/Update times
        testCar.setCreateTime(null);
        testCar.setUpdateTime(null);

        //Get Existing Car
        Car retrievedCar = carDao.getById(1);
        assertNotNull(retrievedCar);

        //Compare Cars
        assertEquals(testCar,retrievedCar);
    }

   /**
    * Verifies a car is returned correctly based on car search
    */
    @Test
    void getByPropertyExactSuccess() {
        List<Car> carList = carDao.getByPropertyEqual("make", "Jeep");
        assertEquals(1, carList.size());
    }

    /**
     * Verifies a car is returned correctly based on car search
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<Car> carList = carDao.getByPropertyLike("make","Je") ;
        assertEquals(1, carList.size());
    }

    /**
     * Verify successful insert of a car
     */
    @Test
    void insertSuccess() {
        int insertedCarId;

        //Get User
        GenericDao<User>  userDao = new GenericDao(User.class);
        User user = userDao.getById(1);

        //Create Inserted Car
        Car insertedCar = new Car(user,"2016","Honda","Civic","19XFC1F35GE206053");

        //Save Inserted Car
        insertedCarId = carDao.insert(insertedCar);

        //Get Saved Car
        Car retrievedCar = carDao.getById(insertedCarId);
        assertNotNull(retrievedCar);

        //Compare Cars
        assertEquals(insertedCar,retrievedCar);
     }

    /**
     * Verify successful delete of car, repairs and parts
     *
     */
    @Test
    void deleteSuccess() {
        carDao.delete(carDao.getById(1));
        assertNull(carDao.getById(1));

        //Delete repairs
        GenericDao<Repair> repairDao = new GenericDao(Repair.class);
        Repair repair = repairDao.getById(2);
        assertNull(carDao.getById(2));

        //Delete part
        GenericDao<Part> partDao = new GenericDao(Part.class);
        Role part = partDao.getById(2);
        assertNull(partDao.getById(2));
    }

    /**
     * Verify successful update of car
     */
    @Test
    void updateSuccess() {
        String Vin = "1111111111111111X";
        Car carToUpdate = carDao.getById(1);
        carToUpdate.setVin(Vin);
        carDao.saveOrUpdate(carToUpdate);
        Car retrievedCar = carDao.getById(1);
        assertEquals(carToUpdate, retrievedCar);
    }

}


