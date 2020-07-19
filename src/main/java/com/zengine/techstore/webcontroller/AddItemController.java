package com.zengine.techstore.webcontroller;

import com.zengine.techstore.dto.ItemDto;
import com.zengine.techstore.model.Item;
import com.zengine.techstore.repository.UserRepository;
import com.zengine.techstore.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/addItem")
public class AddItemController {
    @Autowired
    private ItemService service;

    @ModelAttribute("item")
    public ItemDto itemDto(){
        return new ItemDto();
    }

    @GetMapping
    public String showAddItem(Model model){
        return "addItem";
    }

    @PostMapping
    public String addItem(@ModelAttribute("item") @Validated ItemDto itemDto,
                          BindingResult result, Principal principal) throws IOException {
//        System.out.println(principal.getName());
        itemDto.setEmail(principal.getName());
//        System.out.println(itemDto);
//        service.save(itemDto);
        service.save(itemDto);
        return "redirect:/MyItem?success";
    }
}
