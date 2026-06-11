package org.exam.java.spring.asset_manager.controller;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/searchByName")
    public String searchByName(@RequestParam(name = "name") String name, Model model) {

        List<Asset> assets;

        if (name != null && !name.isBlank()) {
            assets = assetService.findByName(name);
        } else {
            assets = assetService.findAll();
        }

        model.addAttribute("assets", assets);

        model.addAttribute("content", "assets/index");
        return "layout/main";
    }

    @GetMapping("/searchByTicker")
    public String searchByTicker(@RequestParam(name = "ticker") String ticker, Model model) {

        List<Asset> assets;

        if (ticker != null && !ticker.isBlank()) {
            assets = assetService.findByTicker(ticker);
        } else {
            assets = assetService.findAll();
        }

        model.addAttribute("assets", assets);

        model.addAttribute("content", "assets/index");
        return "layout/main";
    }

    // @GetMapping("/searchByPrice")
    // public String searchByPrice(@RequestParam(name = "price") BigDecimal price,
    // Model model) {

    // List<Asset> assets;

    // if (price != null) {
    // assets = assetService.findByPrice(price);
    // } else {
    // assets = assetService.findAll();
    // }

    // model.addAttribute("assets", assets);

    // return "assets/index";
    // }

    @GetMapping("/searchByTickerOrName")
    public String searchByTickerOrName(@RequestParam(name = "query") String query, Model model) {

        List<Asset> assets;

        if (query != null && !query.isBlank()) {
            assets = assetService.findByTickerOrName(query);
        } else {
            assets = assetService.findAll();
        }

        model.addAttribute("assets", assets);

        model.addAttribute("content", "assets/index");
        return "layout/main";
    }

    // CREATE

}
