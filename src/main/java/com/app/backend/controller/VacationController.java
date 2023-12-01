package com.app.backend.controller;

import com.app.backend.model.CartItem;
import com.app.backend.model.Excursion;
import com.app.backend.model.Vacation;
import com.app.backend.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacations")
public class VacationController {

    @Autowired
    private VacationService vacationService;

    @GetMapping
    public ResponseEntity<List<Vacation>> getAllVacations() {
        List<Vacation> vacations = vacationService.listOfAllVacations();
        return new ResponseEntity<>(vacations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vacation> getVacationById(@PathVariable Long id) {
        Vacation vacation = vacationService.getVacationById(id);
        if (vacation != null) {
            return new ResponseEntity<>(vacation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Vacation> addVacation(@RequestBody Vacation vacation) {
        Vacation savedVacation = vacationService.addVacation(vacation);
        return new ResponseEntity<>(savedVacation, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vacation> updateVacation(@PathVariable Long id, @RequestBody Vacation vacation) {
        Vacation updatedVacation = vacationService.updateVacation(id, vacation);
        if (updatedVacation != null) {
            return new ResponseEntity<>(updatedVacation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVacation(@PathVariable Long id) {
        vacationService.deleteVacation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/cartItems")
    public ResponseEntity<List<CartItem>> getCartItemsByVacationId(@PathVariable Long id) {
        List<CartItem> cartItems = vacationService.getCartItemsByVacationId(id);
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }
}
