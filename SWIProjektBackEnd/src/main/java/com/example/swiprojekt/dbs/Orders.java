package com.example.swiprojekt.dbs;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table
public class Orders {
    @Id
    @SequenceGenerator(
            name = "orders_generator",
            sequenceName = "orders_generator",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "orders_generator"
    )
    @NonNull
    private int Id;
    @NonNull
    private String carName;
    private double price;
    private LocalDate date;
}
