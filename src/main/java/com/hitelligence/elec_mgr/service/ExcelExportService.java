package com.hitelligence.elec_mgr.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hitelligence.elec_mgr.model.ElecCompanies;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

@Service
public class ExcelExportService {

    private final ElecCompaniesService elecCompaniesService;

    public ExcelExportService(ElecCompaniesService elecCompaniesService) {
        this.elecCompaniesService = elecCompaniesService;
    }

    public InputStream exportElecCompaniesToExcel() throws Exception {
        // 调用 getPagedElecCompanies 方法并获取所有数据
        Page<ElecCompanies> page = elecCompaniesService.getPagedElecCompanies(1, Integer.MAX_VALUE);
        List<ElecCompanies> elecCompanies = page.getRecords();

        // 创建工作簿和工作表
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("电力公司信息");

        // 创建表头
        String[] columns = {"ID", "序号", "地名", "经度", "纬度"};
        Row headerRow = sheet.createRow(0);
        CellStyle headerCellStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerCellStyle.setBorderTop(BorderStyle.THIN);
        headerCellStyle.setBorderBottom(BorderStyle.THIN);
        headerCellStyle.setBorderLeft(BorderStyle.THIN);
        headerCellStyle.setBorderRight(BorderStyle.THIN);

        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        // 填充数据
        CellStyle dataCellStyle = workbook.createCellStyle();
        dataCellStyle.setAlignment(HorizontalAlignment.CENTER);
        dataCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        dataCellStyle.setBorderTop(BorderStyle.THIN);
        dataCellStyle.setBorderBottom(BorderStyle.THIN);
        dataCellStyle.setBorderLeft(BorderStyle.THIN);
        dataCellStyle.setBorderRight(BorderStyle.THIN);

        int rowIdx = 1;
        for (ElecCompanies company : elecCompanies) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(company.getCompanyId() != null ? company.getCompanyId() : 0);
            row.createCell(1).setCellValue(company.getCompanyNo() != null ? company.getCompanyNo() : "");
            row.createCell(2).setCellValue(company.getCompanyName() != null ? company.getCompanyName() : "");
            row.createCell(3).setCellValue(company.getLng() != null ? company.getLng() : 0.0);
            row.createCell(4).setCellValue(company.getLat() != null ? company.getLat() : 0.0);

            for (int i = 0; i < 5; i++) {
                row.getCell(i).setCellStyle(dataCellStyle);
            }
        }

        // 自动调整列宽
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
            int currentWidth = sheet.getColumnWidth(i);
            sheet.setColumnWidth(i, currentWidth + 1024);  // 增加一点额外宽度，避免内容被折叠
        }

        // 将工作簿写入输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();

        return new ByteArrayInputStream(out.toByteArray());
    }
}