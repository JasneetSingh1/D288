package com.app.backend.controller;

import com.app.backend.model.Country;
import com.app.backend.model.Division;
import com.app.backend.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable Long id) {
        Country country = countryService.getCountryById(id);
        if (country != null) {
            return new ResponseEntity<>(country, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Country> addCountry(@RequestBody Country country) {
        Country savedCountry = countryService.addCountry(country);
        return new ResponseEntity<>(savedCountry, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable Long id, @RequestBody Country country) {
        Country updatedCountry = countryService.updateCountry(id, country);
        if (updatedCountry != null) {
            return new ResponseEntity<>(updatedCountry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        countryService.deleteCountry(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/divisions")
    public ResponseEntity<List<Division>> getDivisionsByCountryId(@PathVariable Long id) {
        List<Division> divisions = countryService.getDivisionsByCountryId(id);
        return new ResponseEntity<>(divisions, HttpStatus.OK);
    }
}

