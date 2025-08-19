package com.lgcns.study_micros.statistic;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
