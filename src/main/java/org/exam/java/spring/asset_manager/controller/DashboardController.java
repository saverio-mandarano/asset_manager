package org.exam.java.spring.asset_manager.controller;

import java.util.List;

import org.exam.java.spring.asset_manager.model.Asset;
import org.exam.java.spring.asset_manager.model.Category;
import org.exam.java.spring.asset_manager.model.Tag;
import org.exam.java.spring.asset_manager.service.AssetService;
import org.exam.java.spring.asset_manager.service.CategoryService;
import org.exam.java.spring.asset_manager.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class DashboardController {

    @Autowired
    private AssetService assetService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @GetMapping
    public String index(Model model) {
        List<Asset> assets = assetService.findAll();
        List<Category> categories = categoryService.findAll();
        List<Tag> tags = tagService.findAll();

        model.addAttribute("assets", assets);
        model.addAttribute("categories", categories);
        model.addAttribute("tags", tags);

        model.addAttribute("assetCount", assets.size());
        model.addAttribute("categoryCount", categories.size());
        model.addAttribute("tagCount", tags.size());

        model.addAttribute("pageTitle", "Backoffice Dashboard");

        int uncategorized = 0;
        for (Asset asset : assets) {
            if (asset.getCategory() == null) {
                uncategorized++;
            }
        }
        model.addAttribute("uncategorized", uncategorized);

        int untagged = 0;
        for (Asset asset : assets) {
            if (asset.getTags() == null || asset.getTags().isEmpty()) {
                untagged++;
            }
        }
        model.addAttribute("untagged", untagged);

        model.addAttribute("content", "dashboards/index");
        return "layout/main";
    }
}
