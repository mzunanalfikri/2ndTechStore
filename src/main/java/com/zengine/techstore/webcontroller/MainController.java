package com.zengine.techstore.webcontroller;

import com.zengine.techstore.dto.Filter;
import com.zengine.techstore.model.Item;
import com.zengine.techstore.repository.ItemRepository;
import com.zengine.techstore.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MainController {
    @Autowired
    private ItemService service;

    @GetMapping("/")
    public String root(Principal principal, Model model) throws UnsupportedEncodingException {
        if (principal == null){
            List<Item> myItem = service.getAllItem();
            List<List<Item>> itemShow = new ArrayList<>();
            List<Item> temp ;
            for (int i = 0; i < myItem.size() ; i+=3) {
                temp = new ArrayList<>();
                if (i < myItem.size()) temp.add(myItem.get(i));
                if (i+1 < myItem.size()) temp.add(myItem.get(i+1));
                if (i+2 < myItem.size()) temp.add(myItem.get(i+2));
                itemShow.add(temp);
            }
            model.addAttribute("items", itemShow);
            model.addAttribute("filter", new Filter());
            return "root";
        } else {
            return "redirect:/home";
        }
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }

    @PostMapping()
    public String filterItem(@ModelAttribute("filter") @Validated Filter filter, Model model, Principal principal) throws UnsupportedEncodingException {
        System.out.println(filter);

        List<Item> myItem = service.getAllItem();
        if (!filter.getCategory().equals("All Category")){
            myItem = myItem.stream().filter(s -> s.getCategory().equals(filter.getCategory())).collect(Collectors.toList());
        }
        if (filter.getKey() != null){
            myItem = myItem.stream()
                    .filter(s -> s.getTitle().toLowerCase().contains(filter.getKey().toLowerCase()))
                    .collect(Collectors.toList());
        }
        List<List<Item>> itemShow = new ArrayList<>();
        List<Item> temp ;
        for (int i = 0; i < myItem.size() ; i+=3) {
            temp = new ArrayList<>();
            if (i < myItem.size()) temp.add(myItem.get(i));
            if (i+1 < myItem.size()) temp.add(myItem.get(i+1));
            if (i+2 < myItem.size()) temp.add(myItem.get(i+2));
            itemShow.add(temp);
        }
        model.addAttribute("items", itemShow);
        model.addAttribute("user", principal);
        return "root";
    }
}
