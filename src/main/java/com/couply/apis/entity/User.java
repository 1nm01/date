package com.couply.apis.entity;

//import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
public class User {
    @Id
    private Long mobile;
    private String password;
    private String first_name;
    private String last_name;
    private String gender;
    private String choice;
    private String bio;
//    private List<String> photos;


    @Override
    public String toString() {
        return "User{" +
                "user_id=" +
                ", mobile=" + mobile +
                ", password='" + password + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", gender='" + gender + '\'' +
                ", choice='" + choice + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }

    public User(Long mobile, String password) {
        this.mobile = mobile;
        this.password = password;
    }

    public User(String password) {
        this.password = password;
    }

    public User() {
    }
    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public User( Long mobile, String password, String first_name, String last_name, String gender, String choice, String bio) {
//        this.user_id = user_id;
        this.mobile = mobile;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.choice = choice;
        this.bio = bio;
    }

    public String getGender() {
        return gender;
    }

//    public Long getUser_id() {
//        return user_id;
//    }
//
//    public void setUser_id(Long user_id) {
//        this.user_id = user_id;
//    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

//    public List<String> getPhotos() {
//        return photos;
//    }
//
//    public void setPhotos(List<String> photos) {
//        this.photos = photos;
//    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }
}
