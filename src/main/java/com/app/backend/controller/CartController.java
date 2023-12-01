package com.app.backend.controller;

import com.app.backend.model.Cart;
import com.app.backend.model.CartItem;
import com.app.backend.service.CartService;
import com.app.backend.service.CheckoutServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long id) {
        Cart cart = cartService.getCartById(id);
        if (cart != null) {
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Cart>> allCarts() {
        List<Cart> carts = cartService.allCarts();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cart> addCart(@RequestBody Cart cart) {
        Cart savedCart = cartService.addCart(cart);
        return new ResponseEntity<>(savedCart, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long id, @RequestBody Cart cart) {
        Cart updatedCart = cartService.updateCart(id, cart);
        if (updatedCart != null) {
            return new ResponseEntity<>(updatedCart, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/cartItems")
    public ResponseEntity<List<CartItem>> getCartItemsByCartId(@PathVariable Long id) {
        List<CartItem> cartItems = cartService.getCartItemsByCartId(id);
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }



}

