package com.example.swiprojekt.controller;

import com.example.swiprojekt.dbs.Orders;
import com.example.swiprojekt.dbs.Users;
import com.example.swiprojekt.user.LoginForm;
import com.example.swiprojekt.user.UserForm;
import com.example.swiprojekt.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="user/")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {this.userService = userService;}

    @PostMapping("/register")
    public ResponseEntity<Object> registration(@RequestBody UserForm userForm){
        return userService.register(userForm);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginForm loginForm){
        return userService.login(loginForm);
    }

    @GetMapping("/position")
    public ResponseEntity<Object> getPositions(@RequestParam String pos){
        return userService.getPosition(pos);
    }

    @PostMapping("/changePw")
    public ResponseEntity<Object> changePw(@RequestParam String name,@RequestParam String pw){
        return userService.changePw(name,pw);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Users>> getAllOrders(){return userService.getAllUsers();}

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteUser(@RequestParam String name){
        return userService.deleteUser(name);
    }
}
