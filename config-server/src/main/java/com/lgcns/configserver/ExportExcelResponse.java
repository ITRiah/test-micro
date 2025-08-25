package com.lgcns.configserver;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExportExcelResponse {
    private byte[] fileContents;
    private String fileName;
}

