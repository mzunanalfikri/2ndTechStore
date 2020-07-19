package com.zengine.techstore.service;

import com.zengine.techstore.dto.ItemDto;
import com.zengine.techstore.model.Item;
import com.zengine.techstore.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository repository;

    public List<Item> getItemByEmail(String email) throws UnsupportedEncodingException {
        List<Item> item = repository.findByEmail(email);
        for(Item i : item){
            i.setImageEncoded(new String(Base64.getEncoder().encode(i.getImage()),"UTF-8"));
        }
        return item;
    }

    public List<Item> getAllItem() throws UnsupportedEncodingException {
        List<Item> item = repository.findAll();
        for (Item i : item){
            i.setImageEncoded(new String(Base64.getEncoder().encode(i.getImage()),"UTF-8"));
        }
        return item;
    }
    public Item save(ItemDto dto) throws IOException {
        Item item = new Item();
        item.setTitle(dto.getTitle());
        item.setDescription(dto.getDescription());
        item.setCategory(dto.getCategory());
        item.setEmail(dto.getEmail());
        item.setImage(dto.getFileImage().getBytes());
        item.setPrice(dto.getPrice());
        repository.save(item);
        return item;
    }

    public void deleteItemUsingId(Long id){
        repository.deleteById(id);
    }

}
