package com.couply.apis.services.user;

import com.couply.apis.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserOp {
    public String checkAuth(Long mobile,String password);
    public void saveUser(User user);
    public Optional<User> getUser(Long mobile);
}
