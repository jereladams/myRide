package com.myRide.persistence;

import com.myRide.entity.User;
import com.myRide.testUtils.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    GenericDao<User>  userDao;

    /**
     * Creating the dao.
     */
    @BeforeEach
    void setUp() {
        userDao = new GenericDao(User.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Verifies gets all users successfully.
     */
    @Test
    void getAllUsersSuccess() {
        List<User> users = userDao.getAll();
        assertNotNull(users);
        assertEquals(6, users.size());
    }

    /**
     * Verifies a user is returned correctly based on id search
     */
    @Test
    void getByIdSuccess() {
        User retrievedUser = userDao.getById(1);
        assertNotNull(retrievedUser);
        assertEquals("aaaaaa",retrievedUser.getPassword());
    }

    @Test
    void getByPropertyExactSuccess() {
        List<User> userList = userDao.getByPropertyEqual("email","a@email.com") ;
        assertEquals(1, userList.size());
    }

    /**
     * Verifies a role is returned correctly based on email search
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<User> userList = userDao.getByPropertyLike("email","a@") ;
        assertEquals(1, userList.size());
    }

    /**
     * Verify successful insert of a user
     */
    @Test
    void insertSuccess() {
        User newUser = new User("g@email.com", "gggggg");
        int id = userDao.insert(newUser);
        assertNotEquals(0,id);
        User insertedUser = userDao.getById(id);
        assertNotNull(insertedUser);
        assertEquals(insertedUser,newUser);
    }

    /**
     * Verify successful delete of user
     *
     * Note: added some extra tests for practice
     */
    @Test
    void deleteSuccess() {
        userDao.delete(userDao.getById(3));
        assertNull(userDao.getById(3));
    }

    /**
     * Verify successful update of user
     */
    @Test
    void updateSuccess() {
        String password = "abcdef";
        User userToUpdate = userDao.getById(3);
        userToUpdate.setPassword(password) ;
        userDao.saveOrUpdate(userToUpdate);
        User retrievedUser = userDao.getById(3);
        assertEquals(userToUpdate, retrievedUser);
    }

}

