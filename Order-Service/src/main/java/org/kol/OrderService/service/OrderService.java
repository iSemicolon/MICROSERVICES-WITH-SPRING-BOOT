package org.kol.OrderService.service;

import org.kol.OrderService.entity.Order;
import org.kol.OrderService.repository.OrderRepository;
import org.kol.OrderService.response.OrderResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    public OrderResponse saveOrder(Order order) {
      Order saveOrder=  orderRepository.save(order);

      OrderResponse saveOrderResponse=modelMapper.map(saveOrder,OrderResponse.class);
      return saveOrderResponse;
    }

    public OrderResponse updateOrders(Long orderId, Order order) {

        Order savedOrder=orderRepository.findById(orderId).get();

        savedOrder.setId(order.getId());
        savedOrder.setName(order.getName());
        savedOrder.setType(order.getType());
        savedOrder.setProductName(order.getProductName());

        Order updateOrder=orderRepository.save(savedOrder);

        OrderResponse updateOrderResponse=modelMapper.map(updateOrder,OrderResponse.class);

        return updateOrderResponse;

    }

    public OrderResponse findOrdersById(Long orderId) {

        Order findOrderById=orderRepository.findById(orderId).get();

        OrderResponse findOrderResponse=modelMapper.map(findOrderById,OrderResponse.class);

        return findOrderResponse;

    }

    public List<OrderResponse> findAllOrders() {

        List<Order> fetchAllOrder=orderRepository.findAll();

       List<OrderResponse> orderResponse = fetchAllOrder
               .stream()
               .map(orderResponses -> modelMapper.map(orderResponses, OrderResponse.class))
               .collect(Collectors.toList());

        return orderResponse;

    }


    public String DeleteOrder(Long orderId) {


        orderRepository. deleteById(orderId);

        return "Record Deleted";
    }
}
