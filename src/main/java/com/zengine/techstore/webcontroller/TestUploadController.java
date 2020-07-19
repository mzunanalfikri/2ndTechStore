package com.zengine.techstore.webcontroller;

import com.zengine.techstore.model.Item;
import com.zengine.techstore.model.User;
import com.zengine.techstore.repository.ItemRepository;
import com.zengine.techstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class TestUploadController {
    public static final String UPLOAD_DIR = "src/main/resources/static/image";

    @Autowired
    private ItemRepository repository;

    @GetMapping("/upload")
    public String upload(Model model) throws Exception{
        //file system
//        Path dir = Paths.get(UPLOAD_DIR);
//        List<String> files = new ArrayList<>();
//        try {
//            files = Files.walk(dir, 1)
//                    .map(path -> dir.relativize(path).toString())
//                    .filter(path -> !path.equals(""))
//                    .collect(Collectors.toList());
//        } catch (IOException e){
//            System.out.println(e.getMessage());
//        }
//        System.out.println(files);
//        model.addAttribute("files", files);
        //database
        Optional<Item> item = repository.findById(4L);
        byte[] encode = Base64.getEncoder().encode(item.get().getImage());
        String img = new String(encode, "UTF-8");
        model.addAttribute("msg", true);
        model.addAttribute("img", img);
        return "upload";
    }

    @PostMapping("/upload")
    public String up(@RequestParam(name="file")MultipartFile file, Principal principal){
        //file system
//        System.out.println("post created");
//        try {
//            String path = UPLOAD_DIR + "/" + file.getOriginalFilename();
//            System.out.println("Copying file " + path);
////            FileCopyUtils.copy(file.getBytes(), new File(path));
//            file.transferTo(Paths.get(path));
//        } catch (IOException e){
//            System.out.println("Error upload image");
//        }
        //database
        Item item = new Item();
        byte[] image = null;
        try {
            image = file.getBytes();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        item.setTitle("Barang 123");
        item.setDescription("ini barang lhooo");
        item.setImage(image);
        repository.save(item);

        return "redirect:/upload";
    }

}
