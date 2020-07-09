package com.zengine.techstore.webcontroller;

import com.zengine.techstore.model.User;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TestUploadController {
    public static final String UPLOAD_DIR = "src/main/resources/static/image";

    @GetMapping("/upload")
    public String upload(Model model){
        Path dir = Paths.get(UPLOAD_DIR);
        List<String> files = new ArrayList<>();
        try {
            files = Files.walk(dir, 1)
                    .map(path -> dir.relativize(path).toString())
                    .filter(path -> !path.equals(""))
                    .collect(Collectors.toList());
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        System.out.println(files);
        model.addAttribute("files", files);
        return "upload";
    }

    @PostMapping("/upload")
    public String up(@RequestParam(name="file")MultipartFile file){
        System.out.println("post created");
        try {
            String path = UPLOAD_DIR + "/" + file.getOriginalFilename();
            System.out.println("Copying file " + path);
//            FileCopyUtils.copy(file.getBytes(), new File(path));
            file.transferTo(Paths.get(path));
        } catch (IOException e){
            System.out.println("Error upload image");
        }
        return "redirect:/upload";
    }

}
