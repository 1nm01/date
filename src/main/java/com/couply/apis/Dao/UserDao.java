package com.couply.apis.Dao;

import com.couply.apis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface UserDao extends JpaRepository<User,Long> {
    @Query("SELECT t from User t where t.mobile=mobile")
    List<User> getUser(Long mobile);

//    @Modifying
//    @Query("UPDATE User u SET u.first_name=user.first_name,u.last_name=user.last_name,u.gender=user.gender,u.choice=user.choice,u.bio=user.bio WHERE u.mobile=user.mobile")
//    User addDetails(@Param("user") User user);
    @Modifying
    @Transactional
    @Query("UPDATE User SET first_name=:first_name,last_name=:last_name,gender=:gender,choice=:choice,bio=:bio where mobile=:mobile")
    public void addDetails(@Param("first_name") String first_name,@Param("last_name") String last_name,@Param("gender") String gender,@Param("choice") String choice,@Param("bio") String bio,@Param("mobile") Long mobile);

    @Modifying
    @Transactional
    @Query(value="insert into user (mobile,password,first_name,last_name,gender,choice,bio) values (:mobile,:password,:first_name,:last_name,:gender,:choice,:bio)" ,  nativeQuery = true)
    public void addUser(@Param("first_name") String first_name,@Param("last_name") String last_name,@Param("gender") String gender,@Param("choice") String choice,@Param("bio") String bio,@Param("mobile") Long mobile,@Param("password") String password);
}
