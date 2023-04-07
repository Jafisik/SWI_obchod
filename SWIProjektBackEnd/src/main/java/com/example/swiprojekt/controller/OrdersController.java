package com.example.swiprojekt.controller;

import com.example.swiprojekt.dbs.Orders;
import com.example.swiprojekt.orders.OrdersForm;
import com.example.swiprojekt.orders.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path="orders/")
public class OrdersController {
    private final OrdersService orderService;

    @Autowired
    public OrdersController(OrdersService orderService){
        this.orderService = orderService;
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getOrder(@RequestParam String cn){
        return orderService.getOrder(cn);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Orders>> getAllOrders(){return orderService.getAllOrders();}

    @GetMapping("/getPriceOver")
    public ResponseEntity<Object> getOrderOver(@RequestParam String p){
        return orderService.getOrderOver(p);
    }

    @GetMapping("/getPriceUnder")
    public ResponseEntity<Object> getOrderUnder(@RequestParam String p){
        return orderService.getOrderUnder(p);
    }

    @GetMapping("/getPriceBetween")
    public ResponseEntity<Object> getOrderBetween(@RequestParam String p1, @RequestParam String p2){
        return orderService.getOrderBetween(p1, p2);
    }

    @GetMapping("/getDateAfter")
    public ResponseEntity<Object> getOrderDateAfter(@RequestParam String d){
        return orderService.getOrderDateAfter(d);
    }

    @GetMapping("/getDateBefore")
    public ResponseEntity<Object> getOrderDateBefore(@RequestParam String d){
        return orderService.getOrderDateBefore(d);
    }

    @GetMapping("/getDateBetween")
    public ResponseEntity<Object> getOrderDateBetween(@RequestParam String d1, @RequestParam String d2){
        return orderService.getOrderDateBetween(d1,d2);
    }

    @PostMapping("/addOrder")
    public ResponseEntity<Object> addNewOrder(@RequestBody OrdersForm ordersForm){
        return orderService.addNewOrder(ordersForm);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteOrder(@RequestParam Integer id){
        return orderService.deleteOrder(id);
    }
}
