package com.dzl.blog2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BackendController {

    @GetMapping("/editor")
    public String editor() {
        return "editor";
    }


}
