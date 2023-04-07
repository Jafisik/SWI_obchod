package com.example.swiprojekt.orders;

import com.example.swiprojekt.dbs.Orders;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrdersRepository extends CrudRepository<Orders,Integer> {

    Orders findOrdersById(Integer id);
    List<Object> findOrdersByCarNameIgnoreCase(String carName);
    List<Orders> findAll();
    List<Object> findOrdersByPriceIsLessThanEqual(double price);
    List<Object> findOrdersByPriceIsGreaterThanEqual(double price);
    List<Object> findOrdersByPriceBetween(double price1, double price2);
    List<Object> findOrdersByDateGreaterThanEqual(LocalDate date);
    List<Object> findOrdersByDateLessThanEqual(LocalDate date);
    List<Object> findOrdersByDateBetween(LocalDate date1,LocalDate date2);
}
