package com.app.backend.controller;

import com.app.backend.model.CartItem;
import com.app.backend.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart-items")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/{id}")
    public ResponseEntity<CartItem> getCartItemById(@PathVariable Long id) {
        CartItem cartItem = cartItemService.getCartItemById(id);
        if (cartItem != null) {
            return new ResponseEntity<>(cartItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<CartItem> addCartItem(@RequestBody CartItem cartItem) {
        CartItem savedCartItem = cartItemService.addCartItem(cartItem);
        return new ResponseEntity<>(savedCartItem, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartItem> updateCartItem(@PathVariable Long id, @RequestBody CartItem cartItem) {
        CartItem updatedCartItem = cartItemService.updateCartItem(id, cartItem);
        if (updatedCartItem != null) {
            return new ResponseEntity<>(updatedCartItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long id) {
        cartItemService.deleteCartItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
