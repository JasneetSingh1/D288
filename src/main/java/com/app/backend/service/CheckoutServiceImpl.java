package com.app.backend.service;

import com.app.backend.dto.PurchaseResponse;
import com.app.backend.model.*;
import com.app.backend.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class CheckoutServiceImpl implements ICheckoutService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VacationRepository vacationRepository;

    @Autowired
    private ExcursionRepository excursionRepository;

/*
    @Override
    public PurchaseResponse checkout(PurchaseRequest purchaseRequest) {

        Cart updatedCart = cartRepository.save(purchaseRequest.getCart());

        List<CartItem> cartItemsList = cartItemRepository.saveAll(purchaseRequest.getCartItems());

        updatedCart.setCartItems(cartItemsList);

        cartRepository.save(updatedCart);
        return new PurchaseResponse(updatedCart.getOrderTrackingNumber());
    }*/


    @Override
    public PurchaseResponse createCartWithOrder(Long customerId, Long vacationId, List<Long> excursionIds) {
        // Fetch the customer, vacation, and excursions from the database
        Customer customer = customerRepository.findById(customerId).orElse(null);
        Vacation vacation = vacationRepository.findById(vacationId).orElse(null);
        List<Excursion> excursions = excursionRepository.findAllById(excursionIds);

        if (customer != null && vacation != null && !excursions.isEmpty()) {
            // Create a new cart
            Cart cart = new Cart();
            cart.setOrderTrackingNumber(UUID.randomUUID().toString());
            cart.setStatus(StatusType.pending);
            cart.setLastUpdate(new Date());
            cart.setCustomer(customer);
            cart.setVacation(vacation);
            cart.setExcursions(excursions);
            cart.setCreateDate(new Date());

            // Calculate the package price for the vacation
            double packagePrice = vacation.getTravelFarePrice();

            // Calculate the package price for excursions
            for (Excursion excursion : excursions) {
                packagePrice += excursion.getExcursionPrice();
            }

            cart.setPackagePrice(packagePrice);

            // Save the cart
            cart = cartRepository.save(cart);

            // Create cart items for vacation and excursions
            CartItem vacationCartItem = new CartItem();
            vacationCartItem.setCart(cart);
            vacationCartItem.setVacation(vacation);
            vacationCartItem.setLastUpdate(new Date());
            vacationCartItem.setCreateDate(new Date());
            vacationCartItem = cartItemRepository.save(vacationCartItem);

            // Create cart items for excursions
            for (Excursion excursion : excursions) {
                CartItem excursionCartItem = new CartItem();
                excursionCartItem.setCreateDate(new Date());
                excursionCartItem.setLastUpdate(new Date());
                excursionCartItem.setCart(cart);
                excursionCartItem.setExcursions(Arrays.asList(excursion)); // Set the excursion for the cart item
                excursionCartItem = cartItemRepository.save(excursionCartItem);
            }

            // Add cart items to the cart
            cart.getCartItems().add(vacationCartItem);

            // Save the updated cart
            cart = cartRepository.save(cart);

            ObjectMapper objectMapper = new ObjectMapper();

            try {
                log.info(objectMapper.writeValueAsString(cart));
            } catch (Exception e) {
                log.info(e.getMessage());
            }
            PurchaseResponse purchaseResponse = new PurchaseResponse();
            purchaseResponse.setOrderTrackingNumber(cart.getOrderTrackingNumber());


            return purchaseResponse;
        } else {
            return null;
        }
    }
}
