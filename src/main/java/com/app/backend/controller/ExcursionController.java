package com.app.backend.controller;

import com.app.backend.model.Excursion;
import com.app.backend.service.ExcursionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/excursions")
public class ExcursionController {

    @Autowired
    private ExcursionService excursionService;

    @GetMapping
    public ResponseEntity<List<Excursion>> getAllExcursions() {
        List<Excursion> excursions = excursionService.listOfAllExcursions();
        return new ResponseEntity<>(excursions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Excursion> getExcursionById(@PathVariable Long id) {
        Excursion excursion = excursionService.getExcursionById(id);
        if (excursion != null) {
            return new ResponseEntity<>(excursion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Excursion> addExcursion(@RequestBody Excursion excursion) {
        Excursion savedExcursion = excursionService.addExcursion(excursion);
        return new ResponseEntity<>(savedExcursion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Excursion> updateExcursion(@PathVariable Long id, @RequestBody Excursion excursion) {
        Excursion updatedExcursion = excursionService.updateExcursion(id, excursion);
        if (updatedExcursion != null) {
            return new ResponseEntity<>(updatedExcursion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExcursion(@PathVariable Long id) {
        excursionService.deleteExcursion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

