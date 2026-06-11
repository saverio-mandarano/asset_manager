package org.exam.java.spring.asset_manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @GetMapping
    public String index(Model model) {
        model.addAttribute("content", "categories/index");

        return "layout/main";
    }
}
