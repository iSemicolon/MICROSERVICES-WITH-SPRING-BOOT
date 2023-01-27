package org.kol.OrderService.controller;

import org.kol.OrderService.entity.Order;
import org.kol.OrderService.response.OrderResponse;
import org.kol.OrderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;


    @PostMapping("/add")
    public ResponseEntity<OrderResponse> addOrder (@RequestBody Order order){

        OrderResponse orderResponse=orderService.saveOrder(order);

        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);

    }


    @PutMapping("/update/{Id}")
    public ResponseEntity<OrderResponse> updateOrder ( @PathVariable ("Id") Long orderId, @RequestBody Order order){

        OrderResponse orderResponse=orderService.updateOrders(orderId,order );

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(orderResponse);
    }

    @GetMapping("/fetch-order-by-id/{Id}")
    public ResponseEntity<OrderResponse> getOrderById (@PathVariable("Id") Long orderId){

        OrderResponse orderResponse=orderService.findOrdersById(orderId);

        return ResponseEntity.status(HttpStatus.FOUND).body(orderResponse);

    }


    @GetMapping("/fetch-all")
    public  ResponseEntity<List<OrderResponse> >getAllOrder (){

      List<OrderResponse> orderResponse=  orderService.findAllOrders();

        return ResponseEntity.status(HttpStatus.OK).body(orderResponse);

    }


    @DeleteMapping("del/{Id}")
    public String DeleteOrderById (@PathVariable ("Id") Long orderId){

       String deleteMsg= orderService.DeleteOrder(orderId);

        return deleteMsg;
    }

}
