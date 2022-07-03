package com.couply.apis.controller;

import com.couply.apis.entity.Image;
import com.couply.apis.entity.Story;
import com.couply.apis.entity.User;

import com.couply.apis.entity.UserImage;
import com.couply.apis.services.image.ImageOp;
import com.couply.apis.services.story.StoryOp;

import com.couply.apis.services.user.UserOp;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.zip.Deflater;
import javax.mail.Multipart;
import javax.mail.internet.*;
import javax.servlet.http.HttpServletRequest;

@RestController
public class Controller {
    @Autowired
    private StoryOp stories;

//    @Autowired
//    private JavaMailSender mailSender;

//    @Autowired
//    private RedisTemplate<String,Object> redis;
    @Autowired
    private UserOp users;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ImageOp imageOp;

    @GetMapping("/stories")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Story> getStories()
    {
//        return stories.getStories().subList(0,3);
        return stories.getStories();
    }
    @PostMapping("/story")
    @CrossOrigin(origins = "http://localhost:3000")
    public Story addStory(@RequestBody Story story)
    {
        return stories.addStory(story);
    }

//    @PostMapping("/login")
//    @CrossOrigin(origins = "http://localhost:3000")
//    public void sendOtp(@RequestBody User user){

//        String to = user.getEmail();//change accordingly
//        String from = "singhanmol493@gmail.com";//change accordingly
//        String subject="Otp for Date app";
//        String body="Otp for date app is "+ otp;
//
//        SimpleMailMessage msg=new SimpleMailMessage();
//        msg.setSubject(subject);
//        msg.setText(body);
//        msg.setTo(to);
//        msg.setFrom(from);
//
//        mailSender.send(msg);
//        HttpResponse<String> response = Unirest.post("https://d7-verify.p.rapidapi.com/send")
//                .header("content-type", "application/json")
//                .header("Authorization", "undefined")
//                .header("X-RapidAPI-Host", "d7-verify.p.rapidapi.com")
//                .header("X-RapidAPI-Key", "2d8f634522msh27c50c416e434a8p197967jsn822cc98982af")
//                .body("{\r\n    \"expiry\": 900,\r\n    \"message\": \"Your otp code is 1234\",\r\n    \"mobile\": 9530570340,\r\n    \"sender_id\": \"SMSInfo\"\r\n}")
//                .asString();
//        loginOp.addLoginData(new Login(user.getEmail(),Integer.toString(otp)));
//    }
//    @PostMapping("/otp")
//    @CrossOrigin(origins = "http://localhost:3000")
//    public void verifyOtp(@RequestBody Login details){
//        String userOtp=details.getOtp();
//        String email=details.getEmail();
//        String actualOtp=loginOp.getOtp(email);
//        if(userOtp.equals(actualOtp))
//        {
//            System.out.println("verified");
//        }
//        else{
//            System.out.println("not verified");
//        }
//    }
    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:3000")
    public String userLogin(@RequestBody User user) {
        return users.checkAuth(user.getMobile(),user.getPassword());
    }

    @PostMapping(value = "/register")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> userDetails() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        boolean isUserPresent=users.getUser(Long.parseLong(parameterMap.get("mobile")[0])).isEmpty();
        if(isUserPresent) {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] passwordInBytes = parameterMap.get("password")[0].getBytes("UTF-8");
            byte[] hashedPasswordInBytes = md5.digest(passwordInBytes);
            BigInteger bigInt = new BigInteger(1, hashedPasswordInBytes);
            String hashtext = bigInt.toString(16);
// Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            User user = new User(Long.parseLong(parameterMap.get("mobile")[0]), hashtext, parameterMap.get("first_name")[0], parameterMap.get("last_name")[0], parameterMap.get("gender")[0], parameterMap.get("choice")[0], parameterMap.get("bio")[0]);

            users.saveUser(user);
            return new ResponseEntity<>("user successfully added", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("User is already there",HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping(value = "/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @CrossOrigin(origins = "http://localhost:3000")
    public void uploadImage(@RequestParam(name = "userDp") MultipartFile image,@RequestParam(name = "caption") String caption) throws IOException {
//        System.out.println(image);
//        System.out.println(caption);
//        image.getBytes();
//        String destination=request.getServletContext().getRealPath(image.getOriginalFilename());
//        System.out.println(destination);
//        image.transferTo(new File(destination));
        byte[] imageArray=image.getBytes();
//        byte[] temp=new byte[100];
//        for(int i=0;i<5;i++)
//        {
//            temp[i]=imageArray[i];
//        }
        Image imageDb=new Image(imageArray,"name.jpg",1);
        imageOp.saveImage(imageDb);
//        System.out.println(image.getUserDp());
//        File dp=new File(image.getUserDp());
//        OutputStream out=new FileOutputStream(new File("C:\\Users\\singh\\Desktop\\image.jpg"));
//        out.write();
//        return users.checkAuth(user.getMobile(),user.getPassword());
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable("id") Integer id)
    {
        ByteArrayResource data=new ByteArrayResource(imageOp.getImage(id));
        return ResponseEntity.ok()
                .contentLength(imageOp.getImage(id).length)
                .header("Content-type","application/octet-stream")
                .header("Content-disposition","attachment; filename=abc.png")
                .body(data);
//        return imageOp.getImage(id);
    }

}
