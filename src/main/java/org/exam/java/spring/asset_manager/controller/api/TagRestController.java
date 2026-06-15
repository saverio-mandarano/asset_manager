package org.exam.java.spring.asset_manager.controller.api;

import java.util.List;
import java.util.Optional;

import org.exam.java.spring.asset_manager.model.Tag;
import org.exam.java.spring.asset_manager.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tags")
public class TagRestController {

    @Autowired
    private TagService tagService;

    // index
    @GetMapping
    public ResponseEntity<List<Tag>> index(@RequestParam(required = false) String name) {
        List<Tag> tags;
        if (name != null && !name.isBlank())

        {
            tags = tagService.findByName(name);
        } else {
            tags = tagService.findAll();
        }

        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    // show
    @GetMapping("/{id}")
    public ResponseEntity<Tag> show(@PathVariable Integer id) {
        Optional<Tag> tagAttempt = tagService.findByIdOptional(id);

        if (tagAttempt.isEmpty()) {
            return new ResponseEntity<Tag>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Tag>(tagAttempt.get(), HttpStatus.OK);
    }

}
