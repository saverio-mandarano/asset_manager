package org.exam.java.spring.asset_manager.controller;

import java.util.List;

import org.exam.java.spring.asset_manager.model.Asset;
import org.exam.java.spring.asset_manager.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/assets")
public class AssetController {

    @Autowired
    private AssetService assetService;

    // READ
    @GetMapping
    public String index(Authentication authentication,
            @RequestParam(name = "sortBy", required = false) String sortBy,
            @RequestParam(name = "direction", required = false) String direction,
            Model model) {

        if (direction == null
                || !(direction.equalsIgnoreCase("asc") || direction.equalsIgnoreCase("desc"))) {
            direction = "asc";
        }

        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC
                : Sort.Direction.ASC;
        List<Asset> assets;

        if (sortBy != null) {
            switch (sortBy) {
                case "ticker":
                    assets = assetService.findAllSorted("ticker", sortDirection);
                    break;
                case "name":
                    assets = assetService.findAllSorted("name", sortDirection);
                    break;
                case "category":
                    assets = assetService.findAllSorted("category.name", sortDirection);
                    break;
                case "price":
                    assets = assetService.findAllSorted("lastPrice", sortDirection);
                    break;
                default:
                    assets = assetService.findAll();
                    sortBy = null;
            }
        } else {
            assets = assetService.findAll();
        }

        model.addAttribute("assets", assets);
        // model.addAttribute("username", authentication.getName());
        model.addAttribute("pageTitle", "Asset list");
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("direction", direction);

        model.addAttribute("content", "assets/index");
        return "layout/main";

    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("asset", assetService.findById(id));
        model.addAttribute("pageTitle", assetService.findById(id).getName() + " details");

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
        model.addAttribute("pageTitle", "Asset list");

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
        model.addAttribute("pageTitle", "Asset list");

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
    // model.addAttribute("pageTitle", "Asset list");

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
        model.addAttribute("pageTitle", "Asset list");

        model.addAttribute("content", "assets/index");
        return "layout/main";
    }

    // CREATE
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("asset", new Asset());
        // model.addAttribute("ingredients", ingredientRepository.findAll());
        model.addAttribute("pageTitle", "Create Asset");

        model.addAttribute("content", "assets/create-or-edit");
        return "layout/main";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("asset") Asset formAsset,
            BindingResult bindingResult, Model model) {
        // model.addAttribute("ingredients", ingredientRepository.findAll());
        model.addAttribute("pageTitle", "Create Asset");

        if (bindingResult.hasErrors()) {
            model.addAttribute("content", "assets/create-or-edit");
            return "layout/main";
        }

        assetService.create(formAsset);
        return "redirect:/assets";
    }

    // UPDATE
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("asset", assetService.findById(id));
        // model.addAttribute("ingredients", ingredientRepository.findAll());
        model.addAttribute("edit", true);
        model.addAttribute("pageTitle", "Edit " + assetService.findById(id).getName());

        model.addAttribute("content", "assets/create-or-edit");
        return "layout/main";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("asset") Asset formAsset,
            BindingResult bindingResult, Model model) {
        // model.addAttribute("ingredients", ingredientRepository.findAll());
        model.addAttribute("pageTitle", "Edit " + formAsset.getName());

        if (bindingResult.hasErrors()) {
            // check***
            model.addAttribute("edit", true);
            model.addAttribute("content", "assets/create-or-edit");
            return "layout/main";
        }

        assetService.update(formAsset);
        return "redirect:/assets/" + formAsset.getId();
    }

    @PostMapping("/{id}/detach")
    public String detach(@PathVariable Integer id, @RequestParam Integer categoryId) {
        assetService.detachFromCategory(id);
        return "redirect:/categories/" + categoryId;
    }

    @PostMapping("/{id}/detach-tag")
    public String detachTag(@PathVariable Integer id, @RequestParam Integer tagId) {

        assetService.detachTag(id, tagId);

        return "redirect:/tags/" + tagId;
    }

    // DELETE
}
