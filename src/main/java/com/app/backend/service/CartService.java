package com.app.backend.service;

import com.app.backend.model.Cart;
import com.app.backend.model.CartItem;
import com.app.backend.model.StatusType;
import com.app.backend.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public Cart getCartById(Long id) {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        return optionalCart.orElse(null);
    }

    public List<Cart> allCarts() {
        return cartRepository.findAll();
    }

    public Cart addCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart updateCart(Long id, Cart updatedCart) {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            // Update the properties of the cart with the properties from updatedCart
            cart.setPackagePrice(updatedCart.getPackagePrice());
            cart.setPartySize(updatedCart.getPartySize());
            cart.setStatus(updatedCart.getStatus());
            cart.setOrderTrackingNumber(updatedCart.getOrderTrackingNumber());
            cart.setCreateDate(updatedCart.getCreateDate());
            cart.setLastUpdate(updatedCart.getLastUpdate());
            cart.setCustomer(updatedCart.getCustomer());
            // Update other properties as needed

            return cartRepository.save(cart);
        } else {
            return null;
        }
    }

    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }

    public List<CartItem> getCartItemsByCartId(Long cartId) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            return cart.getCartItems();
        } else {
            return null;
        }
    }


    public boolean orderStatusChange(String trackingNumber, String status) {

        Optional<Cart> cart = cartRepository.findByOrderTrackingNumber(trackingNumber);

        if (cart.isPresent()) {
            switch (status) {
                case "pending": {
                    cart.get().setStatus(StatusType.pending);
                    break;
                }
                case "ordered": {
                    cart.get().setStatus(StatusType.ordered);
                    break;
                }
                case "cancelled": {
                    cart.get().setStatus(StatusType.cancelled);
                    break;
                }
                default: {
                    return false;
                }
            }

            cart.get().setLastUpdate(new Date());
            cartRepository.save(cart.get());
            return true;
        } else {
            return false;
        }
    }


    public Cart getCartByOrderTracking(String orderTrackingNum) {
        Optional<Cart> cart = cartRepository.findByOrderTrackingNumber(orderTrackingNum);

        if (cart.isPresent()) {
            return cart.get();
        } else {
            throw new RuntimeException("Order doesn't exists in the database.!");
        }
    }
}

