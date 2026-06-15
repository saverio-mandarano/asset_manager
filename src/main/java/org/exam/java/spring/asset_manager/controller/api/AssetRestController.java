package org.exam.java.spring.asset_manager.controller.api;

import java.util.List;
import java.util.Optional;

import org.exam.java.spring.asset_manager.model.Asset;
import org.exam.java.spring.asset_manager.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/assets")
public class AssetRestController {

    @Autowired
    private AssetService assetService;

    // index
    @GetMapping
    public ResponseEntity<List<Asset>> index(@RequestParam(required = false) String name) {
        List<Asset> assets;
        if (name != null && !name.isBlank())

        {
            assets = assetService.findByTickerOrName(name);
        } else {
            assets = assetService.findAll();
        }

        return new ResponseEntity<>(assets, HttpStatus.OK);
    }

    // show
    @GetMapping("/{id}")
    public ResponseEntity<Asset> show(@PathVariable Integer id) {
        Optional<Asset> assetAttempt = assetService.findByIdOptional(id);

        if (assetAttempt.isEmpty()) {
            return new ResponseEntity<Asset>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Asset>(assetAttempt.get(), HttpStatus.OK);

    }
}
