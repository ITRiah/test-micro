package com.lgcns.configserver;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchContractOLRequest {
    private int year;
    private int startMonth;
    private int endMonth;
}

