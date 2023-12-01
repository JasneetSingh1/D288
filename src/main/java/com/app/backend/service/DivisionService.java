package com.app.backend.service;

import com.app.backend.model.Customer;
import com.app.backend.model.Division;
import com.app.backend.repository.DivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DivisionService {

    @Autowired
    private DivisionRepository divisionRepository;

    public Division getDivisionById(Long id) {
        Optional<Division> optionalDivision = divisionRepository.findById(id);
        return optionalDivision.orElse(null);
    }

    public Division addDivision(Division division) {
        return divisionRepository.save(division);
    }

    public Division updateDivision(Long id, Division updatedDivision) {
        Optional<Division> optionalDivision = divisionRepository.findById(id);
        if (optionalDivision.isPresent()) {
            Division division = optionalDivision.get();
            // Update the properties of the division with the properties from updatedDivision
            division.setDivisionName(updatedDivision.getDivisionName());
            division.setCreateDate(updatedDivision.getCreateDate());
            division.setLastUpdate(updatedDivision.getLastUpdate());
            division.setCountry(updatedDivision.getCountry());
            // Update other properties as needed

            return divisionRepository.save(division);
        } else {
            return null;
        }
    }

    public void deleteDivision(Long id) {
        divisionRepository.deleteById(id);
    }

    public List<Customer> getCustomersByDivisionId(Long divisionId) {
        Optional<Division> optionalDivision = divisionRepository.findById(divisionId);
        if (optionalDivision.isPresent()) {
            Division division = optionalDivision.get();
            return division.getCustomers();
        } else {
            return null;
        }
    }
}
