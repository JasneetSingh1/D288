package com.app.backend.service;

import com.app.backend.dto.PurchaseRequest;
import com.app.backend.dto.PurchaseResponse;

import java.util.List;

public interface ICheckoutService {
    //    PurchaseResponse checkout(PurchaseRequest purchaseRequest);
    PurchaseResponse createCartWithOrder(Long customerId, Long vacationId, List<Long> excursionIds);
}