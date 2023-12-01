package com.app.backend.service;

import com.app.backend.model.Country;
import com.app.backend.model.Division;
import com.app.backend.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public Country getCountryById(Long id) {
        Optional<Country> optionalCountry = countryRepository.findById(id);
        return optionalCountry.orElse(null);
    }

    public Country addCountry(Country country) {
        return countryRepository.save(country);
    }

    public Country updateCountry(Long id, Country updatedCountry) {
        Optional<Country> optionalCountry = countryRepository.findById(id);
        if (optionalCountry.isPresent()) {
            Country country = optionalCountry.get();
            // Update the properties of the country with the properties from updatedCountry
            country.setCountryName(updatedCountry.getCountryName());
            country.setCreateDate(updatedCountry.getCreateDate());
            country.setLastUpdate(updatedCountry.getLastUpdate());
            // Update other properties as needed

            return countryRepository.save(country);
        } else {
            return null;
        }
    }

    public void deleteCountry(Long id) {
        countryRepository.deleteById(id);
    }

    public List<Division> getDivisionsByCountryId(Long countryId) {
        Optional<Country> optionalCountry = countryRepository.findById(countryId);
        if (optionalCountry.isPresent()) {
            Country country = optionalCountry.get();
            return country.getDivisions();
        } else {
            return null;
        }
    }
}
