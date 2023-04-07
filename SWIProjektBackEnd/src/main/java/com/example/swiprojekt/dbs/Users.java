package com.example.swiprojekt.dbs;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table
public class Users {
    @jakarta.persistence.Id
    @SequenceGenerator(
            name = "user_generator",
            sequenceName = "user_generator",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_generator"
    )
    @NonNull
    private int Id;
    @NonNull
    private String userName;
    private String password;
    private Positions position;

}
