package net.javaguides.springboot.service.impl;

import net.javaguides.springboot.dto.OrderRequest;
import net.javaguides.springboot.dto.OrderResponse;
import net.javaguides.springboot.entity.Order;
import net.javaguides.springboot.entity.Payment;
import net.javaguides.springboot.exception.PaymentException;
import net.javaguides.springboot.repository.OrderRepository;
import net.javaguides.springboot.repository.PaymentRepository;
import net.javaguides.springboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    final OrderRepository orderRepository;

    final PaymentRepository paymentRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, PaymentRepository paymentRepository) {
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Transactional
    public OrderResponse placeOrder(OrderRequest orderRequest) {

        Order order = orderRequest.getOrder();
        order.setStatus("IN PROGRESS");
        order.setOrderTrackingNo(UUID.randomUUID().toString());

        orderRepository.save(order);

        // write some logic to fail the payment
        Payment payment = orderRequest.getPayment();

        // for our use case we support only debit cards
        if (!payment.getCardType().equals("Debit")) {
            throw new PaymentException("Card type not supported");
        }

        payment.setOrderId(order.getId());
        paymentRepository.save(payment);

        // send response
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderTrackingNumber(order.getOrderTrackingNo());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setMessage("SUCCESS");

        return orderResponse;
    }
}
