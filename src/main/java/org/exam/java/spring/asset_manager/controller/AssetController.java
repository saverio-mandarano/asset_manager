package org.exam.java.spring.asset_manager.controller;

import java.util.List;

import org.exam.java.spring.asset_manager.model.Asset;
import org.exam.java.spring.asset_manager.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/assets")
public class AssetController {

    @Autowired
    private AssetService assetService;

    // READ
    @GetMapping
    public String index(Authentication authentication, Model model) {
        List<Asset> assets = assetService.findAll();
        model.addAttribute("assets", assets);
        model.addAttribute("username", authentication.getName());

        model.addAttribute("content", "assets/index");
        return "layout/main";

    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("asset", assetService.findById(id));

        model.addAttribute("content", "assets/show");
        return "layout/main";
    }

}
