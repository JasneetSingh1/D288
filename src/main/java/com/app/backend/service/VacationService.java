package com.app.backend.service;

import com.app.backend.model.CartItem;
import com.app.backend.model.Excursion;
import com.app.backend.model.Vacation;
import com.app.backend.repository.VacationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VacationService {

    @Autowired
    private VacationRepository vacationRepository;

    public Vacation getVacationById(Long id) {
        Optional<Vacation> optionalVacation = vacationRepository.findById(id);
        return optionalVacation.orElse(null);
    }

    public List<Vacation> listOfAllVacations() {
        return vacationRepository.findAll();
    }
    public Vacation addVacation(Vacation vacation) {
        return vacationRepository.save(vacation);
    }

    public Vacation updateVacation(Long id, Vacation updatedVacation) {
        Optional<Vacation> optionalVacation = vacationRepository.findById(id);
        if (optionalVacation.isPresent()) {
            Vacation vacation = optionalVacation.get();
            // Update the properties of the vacation with the properties from updatedVacation
            vacation.setVacationTitle(updatedVacation.getVacationTitle());
            vacation.setDescription(updatedVacation.getDescription());
            vacation.setTravelFarePrice(updatedVacation.getTravelFarePrice());
            vacation.setCreateDate(updatedVacation.getCreateDate());
            vacation.setLastUpdate(updatedVacation.getLastUpdate());
            // Update other properties as needed

            return vacationRepository.save(vacation);
        } else {
            return null;
        }
    }

    public void deleteVacation(Long id) {
        vacationRepository.deleteById(id);
    }

    public List<CartItem> getCartItemsByVacationId(Long vacationId) {
        Optional<Vacation> optionalVacation = vacationRepository.findById(vacationId);
        if (optionalVacation.isPresent()) {
            Vacation vacation = optionalVacation.get();
            return vacation.getCartItems();
        } else {
            return null;
        }
    }
}
