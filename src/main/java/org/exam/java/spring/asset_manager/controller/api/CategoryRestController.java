package org.exam.java.spring.asset_manager.controller.api;

import java.util.List;
import java.util.Optional;

import org.exam.java.spring.asset_manager.model.Category;
import org.exam.java.spring.asset_manager.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {

    @Autowired
    private CategoryService categoryService;

    // index
    @GetMapping
    public ResponseEntity<List<Category>> index(@RequestParam(required = false) String name) {
        List<Category> categories;
        if (name != null && !name.isBlank())

        {
            categories = categoryService.findByName(name);
        } else {
            categories = categoryService.findAll();
        }

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    // show
    @GetMapping("/{id}")
    public ResponseEntity<Category> show(@PathVariable Integer id) {
        Optional<Category> categoryAttempt = categoryService.findByIdOptional(id);

        if (categoryAttempt.isEmpty()) {
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Category>(categoryAttempt.get(), HttpStatus.OK);

    }

}
