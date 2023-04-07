package com.example.swiprojekt.user;

import com.example.swiprojekt.dbs.Positions;
import lombok.Getter;

@Getter
public class UserForm {
    private String userName;
    private String password;
    private Positions position;
}
