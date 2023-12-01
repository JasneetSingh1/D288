package com.app.backend.service;

import com.app.backend.model.Excursion;
import com.app.backend.repository.ExcursionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExcursionService {

    @Autowired
    private ExcursionRepository excursionRepository;

    public Excursion getExcursionById(Long id) {
        Optional<Excursion> optionalExcursion = excursionRepository.findById(id);
        return optionalExcursion.orElse(null);
    }

    public List<Excursion> listOfAllExcursions() {
        return excursionRepository.findAll();
    }

    public Excursion addExcursion(Excursion excursion) {
        return excursionRepository.save(excursion);
    }

    public Excursion updateExcursion(Long id, Excursion updatedExcursion) {
        Optional<Excursion> optionalExcursion = excursionRepository.findById(id);
        if (optionalExcursion.isPresent()) {
            Excursion excursion = optionalExcursion.get();
            // Update the properties of the excursion with the properties from updatedExcursion
            excursion.setExcursionTitle(updatedExcursion.getExcursionTitle());
            excursion.setExcursionPrice(updatedExcursion.getExcursionPrice());
            excursion.setImageURL(updatedExcursion.getImageURL());
            excursion.setCreateDate(updatedExcursion.getCreateDate());
            excursion.setLastUpdate(updatedExcursion.getLastUpdate());
            // Update other properties as needed

            return excursionRepository.save(excursion);
        } else {
            return null;
        }
    }

    public void deleteExcursion(Long id) {
        excursionRepository.deleteById(id);
    }
}
