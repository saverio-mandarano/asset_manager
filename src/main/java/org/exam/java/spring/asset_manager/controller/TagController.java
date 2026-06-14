package org.exam.java.spring.asset_manager.controller;

import java.util.List;

import org.exam.java.spring.asset_manager.model.Tag;
import org.exam.java.spring.asset_manager.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Sort;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;

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
        List<Tag> tags;

        if (sortBy != null) {
            switch (sortBy) {
                case "name":
                    tags = tagService.findAllSorted("name", sortDirection);
                    break;
                case "type":
                    // tags = tagService.findAllSorted("type", sortDirection);
                    //
                    tags = direction.equalsIgnoreCase("asc")
                            ? tagService.findAllOrderByTypeAsc()
                            : tagService.findAllOrderByTypeDesc();
                    break;
                case "assetsCount":
                    tags = direction.equalsIgnoreCase("asc")
                            ? tagService.findAllOrderByAssetsCountAsc()
                            : tagService.findAllOrderByAssetsCountDesc();
                    break;
                default:
                    tags = tagService.findAll();
                    sortBy = null;
            }
        } else {
            tags = tagService.findAll();
        }

        model.addAttribute("tags", tags);
        model.addAttribute("pageTitle", "Tag list");
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("direction", direction);

        model.addAttribute("content", "tags/index");
        return "layout/main";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("tag", tagService.findById(id));
        model.addAttribute("pageTitle", tagService.findById(id).getName() + " details");

        model.addAttribute("content", "tags/show");
        return "layout/main";
    }

    @GetMapping("/searchByName")
    public String searchByName(@RequestParam(name = "name") String name, Model model) {
        List<Tag> tags;

        if (name != null && !name.isBlank()) {
            tags = tagService.findByName(name);
        } else {
            tags = tagService.findAll();
        }

        model.addAttribute("tags", tags);
        model.addAttribute("pageTitle", "Tag list");

        model.addAttribute("content", "tags/index");
        return "layout/main";
    }

    // CREATE
    @GetMapping("/create")
    // public String create(Authentication authentication, Model model) {
    public String create(Model model) {
        model.addAttribute("tag", new Tag());
        model.addAttribute("pageTitle", "Create Tag");

        model.addAttribute("content", "tags/create-or-edit");
        return "layout/main";

    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("tag") Tag formTag,
            BindingResult bindingResult, Model model) {
        model.addAttribute("pageTitle", "Create Tag");

        if (bindingResult.hasErrors()) {
            model.addAttribute("content", "tags/create-or-edit");
            return "layout/main";
        }

        tagService.create(formTag);
        return "redirect:/tags";
    }

    // Update
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("tag", tagService.findById(id));
        model.addAttribute("edit", true);
        model.addAttribute("pageTitle", "Edit " + tagService.findById(id).getName());

        model.addAttribute("content", "tags/create-or-edit");
        return "layout/main";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("tag") Tag formTag,
            BindingResult bindingResult, Model model) {
        model.addAttribute("pageTitle", "Edit " + formTag.getName());

        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            model.addAttribute("content", "tags/create-or-edit");
            return "layout/main";
        }

        tagService.update(formTag);
        return "redirect:/tags/" + formTag.getId();
    }

    // DELETE
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {

        tagService.deleteById(id);

        return "redirect:/tags";
    }
}
