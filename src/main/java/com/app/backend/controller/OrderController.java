package com.app.backend.controller;

import com.app.backend.dto.PurchaseResponse;
import com.app.backend.model.Cart;
import com.app.backend.service.CartService;
import com.app.backend.service.CheckoutServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private CheckoutServiceImpl checkoutService;

    @Autowired
    private CartService cartService;


    @PostMapping("/buyVacation")
    public ResponseEntity<PurchaseResponse> checkout(@RequestParam(name = "customerId") Long customerId, @RequestParam(name = "vacationId") Long vacationId,
                                                     @RequestBody List<Long> excursion) {
        PurchaseResponse purchaseResponse = checkoutService.createCartWithOrder(customerId, vacationId, excursion);
        return new ResponseEntity<>(purchaseResponse, HttpStatus.OK);
    }


    @GetMapping("/statusChange")
    public ResponseEntity<?> changeOrderStatus(@RequestParam(name = "orderTrackingNumber") String orderTrackingNumber, @RequestParam(name = "status") String status) {
        try {
            boolean response = cartService.orderStatusChange(orderTrackingNumber, status);
            if (response) {
                return new ResponseEntity("Order status has been changed to: " + status, HttpStatus.OK);
            } else {
                return new ResponseEntity("Order status isn't changed due to some issues with status value.", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/getDetails/OrderTrackingNumber")
    public ResponseEntity<?> getOrderDetails(@RequestParam(name = "trackingNumber") String trackingNumber) {
        try {
            Cart cart = cartService.getCartByOrderTracking(trackingNumber);
            if (cart != null) {
                return new ResponseEntity("This order doesn't exists.", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity(cart, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
