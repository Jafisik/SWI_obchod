package com.example.swiprojekt.orders;

import com.example.swiprojekt.dbs.Orders;
import com.example.swiprojekt.dbs.Positions;
import com.example.swiprojekt.dbs.Users;
import com.example.swiprojekt.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.List;

@Service
@AllArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;
    UserService userService;


    public ResponseEntity<Object> getOrder(String subject){
        List<Object> orders = ordersRepository.findOrdersByCarNameIgnoreCase(subject);
        if(!orders.isEmpty()){
            return new ResponseEntity<>(orders,HttpStatus.FOUND);
        }
        else{
            return new ResponseEntity<>("Order not found",HttpStatus.NO_CONTENT);
        }

    }

    public ResponseEntity<List<Orders>> getAllOrders(){
        List<Orders> orders = ordersRepository.findAll();
        if(!orders.isEmpty()){
            return new ResponseEntity<>(orders,HttpStatus.FOUND);
        }
        orders.add(new Orders(0,"Orders is empty",0));
        return new ResponseEntity<>(orders,HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Object> addNewOrder(OrdersForm ordersForm){
        boolean userExists = false, userAllowed = false;
        if(ordersForm.getPrice() > 0){
            ResponseEntity<List<Users>> users = userService.getAllUsers();
            for (int i = 0; i < users.getBody().size(); i++) {
                if(users.getBody().get(i).getId() == ordersForm.getUserId()){
                    userExists = true;
                    if(users.getBody().get(i).getPosition() != Positions.BASE_EMPLOYEE){
                        userAllowed = true;
                    }
                }
            }
            if(userExists){
                if(userAllowed){
                    Orders orders = new Orders();
                    orders.setCarName(ordersForm.getSubject());
                    orders.setPrice(ordersForm.getPrice());
                    orders.setDate(ordersForm.getDate());
                    orders.setUserId(ordersForm.getUserId());
                    ordersRepository.save(orders);
                    return new ResponseEntity<>("Order created", HttpStatus.CREATED);
                }
                return new ResponseEntity<>("This user has low privilege", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("This user doesn't exist", HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>("Wrong price input", HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<Object> deleteOrder(Integer id){
        Orders orders = ordersRepository.findOrdersById(id);
        if(orders != null){
            ordersRepository.delete(orders);
            return new ResponseEntity<>("Order deleted", HttpStatus.OK);
        } else{
            return new ResponseEntity<>("Id not found", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> getOrderOver(String price){
        try{
            List<Object> orders = ordersRepository.findOrdersByPriceIsGreaterThanEqual(Integer.parseInt(price));
            if(orders.isEmpty()){
                return new ResponseEntity<>("No orders over this price",HttpStatus.NO_CONTENT);
            } else{
                return new ResponseEntity<>(orders,HttpStatus.OK);
            }
        } catch (Exception e){
            return new ResponseEntity<>("Price is not a number",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> getOrderUnder(String price){
        try{
            List<Object> orders = ordersRepository.findOrdersByPriceIsLessThanEqual(Integer.parseInt(price));
            if(orders.isEmpty()){
                return new ResponseEntity<>("No orders under this price",HttpStatus.NO_CONTENT);
            } else{
                return new ResponseEntity<>(orders,HttpStatus.OK);
            }
        } catch (Exception e){
            return new ResponseEntity<>("Price is not a number",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> getOrderBetween(String price1, String price2){
        try{
            List<Object> orders = ordersRepository.findOrdersByPriceBetween(Integer.parseInt(price1),Integer.parseInt(price2));
            if(orders.isEmpty()){
                return new ResponseEntity<>("No orders under this price",HttpStatus.NO_CONTENT);
            } else{
                return new ResponseEntity<>(orders,HttpStatus.OK);
            }
        } catch (Exception e){
            return new ResponseEntity<>("Price is not a number",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> getOrderDateAfter(String date){
        String datePattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
        try{
            List<Object> orders = ordersRepository.findOrdersByDateGreaterThanEqual(sdf.parse(date).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            if(orders.isEmpty()){
                return new ResponseEntity<>("No orders after this date found",HttpStatus.NO_CONTENT);
            } else{
                return new ResponseEntity<>(orders,HttpStatus.OK);
            }

        } catch (Exception e){
            return new ResponseEntity<>("Wrong date input",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> getOrderDateBefore(String date){
        String datePattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
        try{
            List<Object> orders = ordersRepository.findOrdersByDateLessThanEqual(sdf.parse(date).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            if(orders.isEmpty()){
                return new ResponseEntity<>("No orders before this date found",HttpStatus.NO_CONTENT);
            } else{
                return new ResponseEntity<>(orders,HttpStatus.OK);
            }

        } catch (Exception e){
            return new ResponseEntity<>("Wrong date input",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> getOrderDateBetween(String date,String date2){
        String datePattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
        try{
            List<Object> orders = ordersRepository.findOrdersByDateBetween(sdf.parse(date).toInstant().atZone(ZoneId.systemDefault()).toLocalDate().minusDays(1),
                    sdf.parse(date2).toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(1));
            if(orders.isEmpty()){
                return new ResponseEntity<>("No orders between these dates found",HttpStatus.NO_CONTENT);
            } else{
                return new ResponseEntity<>(orders,HttpStatus.OK);
            }

        } catch (Exception e){
            return new ResponseEntity<>("Wrong date input",HttpStatus.BAD_REQUEST);
        }
    }
}
