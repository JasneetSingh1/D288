package com.app.backend.controller;

import com.app.backend.model.Customer;
import com.app.backend.model.Division;
import com.app.backend.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/divisions")
public class DivisionController {

    @Autowired
    private DivisionService divisionService;

    @GetMapping("/{id}")
    public ResponseEntity<Division> getDivisionById(@PathVariable Long id) {
        Division division = divisionService.getDivisionById(id);
        if (division != null) {
            return new ResponseEntity<>(division, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Division> addDivision(@RequestBody Division division) {
        Division savedDivision = divisionService.addDivision(division);
        return new ResponseEntity<>(savedDivision, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Division> updateDivision(@PathVariable Long id, @RequestBody Division division) {
        Division updatedDivision = divisionService.updateDivision(id, division);
        if (updatedDivision != null) {
            return new ResponseEntity<>(updatedDivision, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDivision(@PathVariable Long id) {
        divisionService.deleteDivision(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/customers")
    public ResponseEntity<List<Customer>> getCustomersByDivisionId(@PathVariable Long id) {
        List<Customer> customers = divisionService.getCustomersByDivisionId(id);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}
