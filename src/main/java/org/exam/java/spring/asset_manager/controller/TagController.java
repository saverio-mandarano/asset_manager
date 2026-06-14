package org.exam.java.spring.asset_manager.controller;

import java.util.List;

import org.exam.java.spring.asset_manager.model.Tag;
import org.springframework.boot.data.autoconfigure.web.DataWebProperties.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/tags")
public class TagController {

    // @GetMapping
    // public String index(
    // @RequestParam(name = "sortBy", required = false) String sortBy,
    // @RequestParam(name = "direction", required = false) String direction,
    // Model model) {

    // if (direction == null
    // || !(direction.equalsIgnoreCase("asc") ||
    // direction.equalsIgnoreCase("desc"))) {
    // direction = "asc";
    // }

    // Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ?
    // Sort.Direction.DESC
    // : Sort.Direction.ASC;
    // List<Tag> tags;

    // if (sortBy != null) {
    // switch (sortBy) {
    // case "name":
    // categories = categoryService.findAllSorted("name", sortDirection);
    // break;
    // case "riskLevel":
    // // categories = categoryService.findAllSorted("riskLevel", sortDirection);
    // //
    // categories = direction.equalsIgnoreCase("asc")
    // ? categoryService.findAllOrderByRiskLevelAsc()
    // : categoryService.findAllOrderByRiskLevelDesc();
    // break;
    // case "assetsCount":
    // categories = direction.equalsIgnoreCase("asc")
    // ? categoryService.findAllOrderByAssetsCountAsc()
    // : categoryService.findAllOrderByAssetsCountDesc();
    // break;
    // default:
    // categories = categoryService.findAll();
    // sortBy = null;
    // }
    // } else {
    // categories = categoryService.findAll();
    // }

    // model.addAttribute("categories", categories);
    // model.addAttribute("pageTitle", "Category list");
    // model.addAttribute("sortBy", sortBy);
    // model.addAttribute("direction", direction);

    // model.addAttribute("content", "categories/index");
    // return "layout/main";
    // }
}
