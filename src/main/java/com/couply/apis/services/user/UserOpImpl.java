package com.couply.apis.services.user;

import com.couply.apis.Dao.UserDao;
import com.couply.apis.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserOpImpl implements UserOp{
    @Autowired
    private UserDao users;
    @Override
    public String checkAuth(Long mobile, String password) {
        User user=users.findById(mobile).get();
        if(user.getPassword().equals(password))
        {
            return "{\"status\":\"Auth Successful\"}";
        }
        else{
            return "{\"status\":\"Auth failed\"}";
        }

    }


    @Override
    public void saveUser(User user) {
//        users.addUser(user.getFirst_name(), user.getLast_name(),user.getGender(), user.getChoice(), user.getBio(), user.getMobile(), user.getPassword());
        users.save(user);
    }

    @Override
    public Optional<User> getUser(Long mobile)
    {
        return users.findById(mobile);
    }

}
