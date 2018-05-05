package com.myRide.persistence;

import com.myRide.entity.Role;
import com.myRide.entity.User;
import com.myRide.testUtils.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RoleTest {

    GenericDao<Role>  roleDao;

    /**
     * Creating the dao.
     */
    @BeforeEach
    void setUp() {
        roleDao = new GenericDao(Role.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Verifies gets all roles successfully.
     */
    @Test
    void getAllRolesSuccess() {
        List<Role> roles = roleDao.getAll();
        assertNotNull(roles);
        assertEquals(6, roles.size());
    }


    /**
     * Verifies a role is returned correctly based on email search
     */
    @Test
    void getByPropertyExactSuccess() {
        List<Role> roleList = roleDao.getByPropertyEqual("email","a@email.com") ;
        assertEquals(1, roleList.size());
    }

    /**
     * Verifies a role is returned correctly based on email search
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<Role> roleList = roleDao.getByPropertyLike("email","a@") ;
        assertEquals(1, roleList.size());
    }

    /**
     * Verify successful insert of a role
     */
    @Test
    void insertSuccess() {
        //Create User
        GenericDao<User> userDao = new GenericDao(User.class);
        User user = new User("h@email.com", "hhhhhh");
        userDao.insert(user);

        Role newRole = new Role(user,"h@email.com","User");
        int id = roleDao.insert(newRole);
        assertNotEquals(0,id);
        Role insertedRole = roleDao.getById(id);
        assertNotNull(insertedRole);
        assertEquals(insertedRole,newRole);
    }

    /**
     * Verify successful delete of role
     *
     * Note: added some extra tests for practice
     */
    @Test
    void deleteSuccess() {
        roleDao.delete(roleDao.getById(3));
        assertNull(roleDao.getById(3));
    }

    /**
     * Verify successful update of role
     */
    @Test
    void updateSuccess() {
        String role = "Admin";
        Role roleToUpdate = roleDao.getById(3);
        roleToUpdate.setRoleName(role) ;
        roleDao.saveOrUpdate(roleToUpdate);
        Role retrievedRole = roleDao.getById(3);
        assertEquals(roleToUpdate, retrievedRole);
    }

}


