package com.example.uploadfile002.controller;

import com.example.uploadfile002.repository.DBFileRepository;
import com.example.uploadfile002.service.DBFileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebFileController {
    @Autowired
    private DBFileStorageService dbFileStorageService;


    @GetMapping("/bi")
    public String getAllFile(Model model){
        model.addAttribute("listFile", dbFileStorageService.getAllFile());

        return "index";
    }


}
