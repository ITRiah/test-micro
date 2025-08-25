package com.lgcns.configserver;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contracts")
@RequiredArgsConstructor
public class ContractExportController {

    private final ContractExportService contractExportService;

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportContractOL() {
        ExportExcelResponse response = contractExportService.exportSearchContractOL(new SearchContractOLRequest(2025, 7,8));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + response.getFileName())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(response.getFileContents());
    }
}

