package com.example.swiprojekt.user;

import com.example.swiprojekt.dbs.Positions;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserToken {
    private Integer id;
    private String userName;
    private Positions position;
}
