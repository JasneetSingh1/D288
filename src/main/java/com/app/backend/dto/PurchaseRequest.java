package com.app.backend.dto;

import com.app.backend.model.Cart;
import com.app.backend.model.CartItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PurchaseRequest {
    private Cart cart;
    private List<CartItem> cartItems;
}
