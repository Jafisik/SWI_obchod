package com.example.swiprojekt.user;

import com.example.swiprojekt.dbs.Orders;
import com.example.swiprojekt.dbs.Positions;
import com.example.swiprojekt.dbs.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<Users, Integer> {
    boolean existsUserByUserNameIgnoreCase(String name);
    Users findUserByUserNameIgnoreCase(String name);
    List<Object> findUsersByPosition(Positions positions);
    Users getUsersByUserName(String name);
    List<Users> findAll();
}
