package com.zengine.techstore.webcontroller;

import com.zengine.techstore.model.User;
import com.zengine.techstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/test")
    public String test(){
        System.out.println(repository.findAll().toString());
        return "test";
    }

//    @GetMapping("/")
//    public String hometest(){
//        return "test";
//    }
    @GetMapping("/testadd")
    public String testAdd(){
        User user = new User("zuu","zuu@gmail.com", "pass");
        repository.save(user);
        return "test";
    }
}
