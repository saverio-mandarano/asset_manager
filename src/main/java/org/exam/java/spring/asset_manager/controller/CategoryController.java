package org.exam.java.spring.asset_manager.controller;

import java.util.List;

import org.exam.java.spring.asset_manager.model.Category;
import org.exam.java.spring.asset_manager.service.CategoryService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String index(
            @RequestParam(name = "sortBy", required = false) String sortBy,
            @RequestParam(name = "direction", required = false) String direction,
            Model model) {

        if (direction == null
                || !(direction.equalsIgnoreCase("asc") || direction.equalsIgnoreCase("desc"))) {
            direction = "asc";
        }

        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC
                : Sort.Direction.ASC;
        List<Category> categories;

        if (sortBy != null) {
            switch (sortBy) {
                case "name":
                    categories = categoryService.findAllSorted("name", sortDirection);
                    break;
                case "riskLevel":
                    // categories = categoryService.findAllSorted("riskLevel", sortDirection);
                    //
                    categories = direction.equalsIgnoreCase("asc")
                            ? categoryService.findAllOrderByRiskLevelAsc()
                            : categoryService.findAllOrderByRiskLevelDesc();
                    break;
                case "assetsCount":
                    categories = direction.equalsIgnoreCase("asc")
                            ? categoryService.findAllOrderByAssetsCountAsc()
                            : categoryService.findAllOrderByAssetsCountDesc();
                    break;
                default:
                    categories = categoryService.findAll();
                    sortBy = null;
            }
        } else {
            categories = categoryService.findAll();
        }

        model.addAttribute("categories", categories);
        model.addAttribute("pageTitle", "Category list");
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("direction", direction);

        model.addAttribute("content", "categories/index");
        return "layout/main";
    }

    @GetMapping("/{id}")
    public String show(Authentication authentication, @PathVariable("id") Integer id, Model model) {
        model.addAttribute("category", categoryService.findById(id));
        model.addAttribute("username", authentication.getName());
        model.addAttribute("pageTitle", "Category details");

        model.addAttribute("content", "categories/show");
        return "layout/main";
    }

    @GetMapping("/searchByName")
    public String searchByName(Authentication authentication, @RequestParam(name = "name") String name, Model model) {
        List<Category> categories;

        if (name != null && !name.isBlank()) {
            categories = categoryService.findByName(name);
        } else {
            categories = categoryService.findAll();
        }

        model.addAttribute("categories", categories);
        model.addAttribute("username", authentication.getName());
        model.addAttribute("pageTitle", "Category list");

        model.addAttribute("content", "categories/index");
        return "layout/main";
    }

    // CREATE
    @GetMapping("/create")
    // public String create(Authentication authentication, Model model) {
    public String create(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("pageTitle", "Create category");

        model.addAttribute("content", "categories/create-or-edit");
        return "layout/main";

    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("category") Category formCategory,
            BindingResult bindingResult, Model model) {
        model.addAttribute("pageTitle", "Create category");

        if (bindingResult.hasErrors()) {
            model.addAttribute("content", "categories/create-or-edit");
            return "layout/main";
        }

        categoryService.create(formCategory);
        return "redirect:/categories";
    }

    // Update
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("category", categoryService.findById(id));
        model.addAttribute("edit", true);
        model.addAttribute("pageTitle", "Edit category");

        model.addAttribute("content", "categories/create-or-edit");
        return "layout/main";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("category") Category formCategory,
            BindingResult bindingResult, Model model) {
        model.addAttribute("pageTitle", "Edit category");

        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            model.addAttribute("content", "categories/create-or-edit");
            return "layout/main";
        }

        categoryService.update(formCategory);
        return "redirect:/categories/" + formCategory.getId();
    }

    // DELETE
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id,
            RedirectAttributes redirectAttributes) {

        categoryService.deleteById(id);

        return "redirect:/categories";
    }
}
