package com.app.backend.service;

import com.app.backend.model.CartItem;
import com.app.backend.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    public CartItem getCartItemById(Long id) {
        Optional<CartItem> optionalCartItem = cartItemRepository.findById(id);
        return optionalCartItem.orElse(null);
    }

    public CartItem addCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    public CartItem updateCartItem(Long id, CartItem updatedCartItem) {
        Optional<CartItem> optionalCartItem = cartItemRepository.findById(id);
        if (optionalCartItem.isPresent()) {
            CartItem cartItem = optionalCartItem.get();
            // Update the properties of the cartItem with the properties from updatedCartItem
            cartItem.setCreateDate(updatedCartItem.getCreateDate());
            cartItem.setLastUpdate(updatedCartItem.getLastUpdate());
            cartItem.setCart(updatedCartItem.getCart());
            cartItem.setVacation(updatedCartItem.getVacation());
            // Update other properties as needed

            return cartItemRepository.save(cartItem);
        } else {
            return null;
        }
    }

    public void deleteCartItem(Long id) {
        cartItemRepository.deleteById(id);
    }
}
