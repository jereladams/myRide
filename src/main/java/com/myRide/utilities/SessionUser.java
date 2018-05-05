package com.myRide.utilities;

import com.myRide.entity.User;
import com.myRide.persistence.GenericDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SessionUser {

    public User getSessionUser (HttpServletRequest request){

        HttpSession session = request.getSession();

        User user = (User)(session.getAttribute("user"));

        //No user in session
        if (user == null){

            //Get email of logged in user
            String email = request.getRemoteUser();

            if (email != "" ){

                //Get user based on email
                GenericDao<User> userDao = new GenericDao(User.class);
                List<User> userList = userDao.getByPropertyEqual("email",email);

                if (userList.size()> 0) {
                    user = userList.get(0);
                }else{
                    //User not found
                    user = null;
                }

            }else{
                //User not logged in
                user = null;
            }
        }

        return user;
    }
}
