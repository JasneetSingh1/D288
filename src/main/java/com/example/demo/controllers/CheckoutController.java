package com.example.demo.controllers;

import com.example.demo.services.Checkout;
import com.example.demo.services.Purchase;
import com.example.demo.services.PurchaseReply;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController
{

    private Checkout checkout;
    public CheckoutController(Checkout checkout) {
        this.checkout = checkout;
    }

    @PostMapping("/purchase")
    public PurchaseReply placeOrder(@RequestBody Purchase purchase)
    {
        PurchaseReply purchaseResponse = checkout.placeOrder(purchase);
        return purchaseResponse;
    }
}