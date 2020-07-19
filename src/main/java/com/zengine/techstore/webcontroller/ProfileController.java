package com.zengine.techstore.webcontroller;

import com.zengine.techstore.dto.ChangeUserDto;
import com.zengine.techstore.model.User;
import com.zengine.techstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService service;

    @GetMapping
    public String showUser(Model model, Principal principal){
        User user = service.findByEmail(principal.getName());
        model.addAttribute("username", user.getName());
        model.addAttribute("email", user.getEmail());

        return "profile";
    }

    @GetMapping("/change")
    public String changeProfile(Model model){

        model.addAttribute("user", new ChangeUserDto());
        return "changeprofile";
    }

    @PostMapping("/change")
    public String postChangeProfile(@ModelAttribute("user") ChangeUserDto user, Principal principal){
        System.out.println(user);
        user.setEmail(principal.getName());
        service.saveEdit(user);
        return "redirect:/profile?success";
    }
}
