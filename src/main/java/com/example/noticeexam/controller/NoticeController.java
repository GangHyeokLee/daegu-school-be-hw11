package com.example.noticeexam.controller;

import com.example.noticeexam.domain.Notice;
import com.example.noticeexam.service.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class NoticeController {

    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/notices";
    }

    //create
    @GetMapping("/create")
    public String create() {
        return "create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Notice notice,
                         @RequestParam("file") MultipartFile file) throws IOException {
        noticeService.create(notice, file);
        return "readlist";
    }

    //readlist
    @GetMapping("/notices")
    public String notices(Model model) {
        model.addAttribute("notices", noticeService.readlist());
        return "readlist";
    }

    // readdetail
    @GetMapping("/readdetail/{id}")
    public String readdetail(@PathVariable("id") int id, Model model) {
        model.addAttribute("notice", noticeService.findById(id));
        return "readdetail";
    }
}
