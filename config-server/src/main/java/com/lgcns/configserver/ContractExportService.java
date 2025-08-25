package com.lgcns.configserver;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

@Service
public class ContractExportService {

    public ExportExcelResponse exportSearchContractOL(SearchContractOLRequest request) {
        // validate đơn giản
        int start = request.getStartMonth();
        int end = request.getEndMonth();
        int year = request.getYear();
        if (start < 1 || start > 12 || end < 1 || end > 12 || start > end) {
            throw new IllegalArgumentException("Invalid month range");
        }
        int monthsCount = end - start + 1;

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Contract OL");

            // ===== styles =====
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);

            // ===== tạo 2 dòng header =====
            Row row1 = sheet.createRow(0);
            Row row2 = sheet.createRow(1);

            // 1) các cột cố định
            String[] fixedHeaders = {
                    "No", "Contract Code", "Contract Name",
                    "VNB Team", "Contract VNB PMO", "Customer Name",
                    "HQ P.I.C", "Contract Start Date"
            };

            int col = 0;
            for (String h : fixedHeaders) {
                Cell c = row1.createCell(col);
                c.setCellValue(h);
                c.setCellStyle(headerStyle);
                // merge theo chiều dọc (2 dòng)
                sheet.addMergedRegion(new CellRangeAddress(0, 1, col, col));
                col++;
            }

            // 2) 4 group động: Actual MM, Actual Amount, Invoice MM, Invoice Amount
            String[] groupHeaders = {"Actual MM", "Actual Amount", "Invoice MM", "Invoice Amount"};

            for (String group : groupHeaders) {
                int groupStartCol = col;

                // ô tiêu đề group (row1)
                Cell groupCell = row1.createCell(groupStartCol);
                groupCell.setCellValue(group);
                groupCell.setCellStyle(headerStyle);

                // merge ngang bao trùm: 1 ô Total + N ô tháng
                int groupWidth = 1 + monthsCount; // Total + months
                int groupEndCol = groupStartCol + groupWidth - 1;
                sheet.addMergedRegion(new CellRangeAddress(0, 0, groupStartCol, groupEndCol));

                // subheaders (row2): Total + các tháng trong range
                // Total
                Cell totalCell = row2.createCell(col);
                totalCell.setCellValue("Total");
                totalCell.setCellStyle(headerStyle);
                col++;

                // Months
                for (int m = start; m <= end; m++) {
                    Cell monthCell = row2.createCell(col++);
                    monthCell.setCellValue(formatMonthLabel(year, m)); // "Jul-25"
                    monthCell.setCellStyle(headerStyle);
                }
            }

            // 3) cột Status độc lập (sau 4 group)
            Cell statusTop = row1.createCell(col);
            statusTop.setCellValue("Status");
            statusTop.setCellStyle(headerStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 1, col, col));
            col++;

            // autosize sơ bộ (đừng autosize quá nhiều cột để tránh tốn thời gian)
            for (int i = 0; i < col; i++) {
                sheet.autoSizeColumn(i);
            }

            // Freeze 2 dòng đầu
            sheet.createFreezePane(0, 2);

            // xuất file
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);

            String fileName = "Contract_OL_List_" + year + "_" + start + "-" + end + ".xlsx";
            return new ExportExcelResponse(out.toByteArray(), fileName);

        } catch (IOException e) {
            throw new RuntimeException("Export failed: " + e.getMessage(), e);
        }
    }

    private String formatMonthLabel(int year, int month) {
        // "Jul-25" như ảnh (đổi "yy" -> "yyyy" nếu muốn "Jul-2025")
        String mon = Month.of(month).getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        String yy = DateTimeFormatter.ofPattern("yy").format(LocalDate.of(year, month, 1));
        return mon + "-" + yy;
    }
}
