package com.lgcns.statistics.statistic;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "statistic")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Statistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date = new Date();

    public Statistic(String message) {
        this.message = message;
    }
}
