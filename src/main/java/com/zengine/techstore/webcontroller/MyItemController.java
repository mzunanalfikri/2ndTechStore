package com.zengine.techstore.webcontroller;

import com.zengine.techstore.model.Item;
import com.zengine.techstore.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/MyItem")
public class MyItemController {

    @Autowired
    private ItemService service;

    @GetMapping
    public String myItem(Model model, Principal principal) throws Exception{
//        System.out.println(principal.getName());
        List<Item> myItem = service.getItemByEmail(principal.getName());
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
        return "myitem";
    }

    @GetMapping("/deleteItem")
    public String deleteItem(@RequestParam(value = "id") Long id, Model model, Principal principal) throws UnsupportedEncodingException {
        service.deleteItemUsingId(id);
        List<Item> myItem = service.getItemByEmail(principal.getName());
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
        return "redirect:/MyItem?successdeleted";
    }
}
