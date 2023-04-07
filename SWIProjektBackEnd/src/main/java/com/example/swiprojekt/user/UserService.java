package com.example.swiprojekt.user;

import com.example.swiprojekt.dbs.Orders;
import com.example.swiprojekt.dbs.Positions;
import com.example.swiprojekt.dbs.Users;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public ResponseEntity<Object> register(UserForm userForm){
        if(!userRepository.existsUserByUserNameIgnoreCase(userForm.getUserName())){
            Users users = new Users();
            users.setUserName(userForm.getUserName());
            users.setPassword(userForm.getPassword());
            users.setPosition(userForm.getPosition());
            userRepository.save(users);
            return new ResponseEntity<>("User created", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Username already exists",HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<Object> login(LoginForm loginForm){
        Users users = userRepository.findUserByUserNameIgnoreCase(loginForm.getUserName());
        if(users != null){
            if(users.getPassword().equals(loginForm.getPassword())){
                UserToken token = new UserToken(users.getId(), users.getUserName(), users.getPosition());
                return new ResponseEntity<>(token,HttpStatus.OK);
            } else{
                return new ResponseEntity<>("Wrong password",HttpStatus.BAD_REQUEST);
            }
        } else{
            return new ResponseEntity<>("Username doesn't exists",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> getPosition(String position){
        Positions positions = Positions.valueOf(position.toUpperCase());
        List<Object> users = userRepository.findUsersByPosition(positions);
        if(users != null){
            return new ResponseEntity<>(users,HttpStatus.OK);
        } else{
            return new ResponseEntity<>("Position doesn't have any user",HttpStatus.NO_CONTENT);
        }
    }

    public ResponseEntity<Object> changePw(String name,String pw){
        Users users = userRepository.getUsersByUserName(name);
        if(users != null){
            users.setPassword(pw);
            userRepository.save(users);
            return new ResponseEntity<>("Password changed",HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Users>> getAllUsers(){
        List<Users> users = userRepository.findAll();
        if(!users.isEmpty()){
            return new ResponseEntity<>(users,HttpStatus.FOUND);
        }
        users.add(new Users(0,"Table is empty"));
        return new ResponseEntity<>(users,HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Object> deleteUser(String name){
        Users users = userRepository.getUsersByUserName(name);
        if(users != null){
            userRepository.delete(users);
            return new ResponseEntity<>("User deleted",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Username not found",HttpStatus.BAD_REQUEST);
        }
    }
}
