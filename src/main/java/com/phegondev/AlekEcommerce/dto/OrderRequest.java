package com.phegondev.AlekEcommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.phegondev.AlekEcommerce.entity.Payment;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderRequest {

    private BigDecimal totalPrice;
    private List<OrderItemRequest> item;
    private Payment paymentInfo;

}
