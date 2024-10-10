package com.hitelligence.elec_mgr.controller;

import com.hitelligence.elec_mgr.service.ExcelExportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

@RestController
public class ExcelExportController {

    private final ExcelExportService excelExportService;

    public ExcelExportController(ExcelExportService excelExportService) {
        this.excelExportService = excelExportService;
    }

    @GetMapping("/download_elec_companies")
    public void downloadElecCompanies(HttpServletResponse response) {
        try {
            InputStream excelStream = excelExportService.exportElecCompaniesToExcel();

            // 设置响应头，告诉浏览器下载文件
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=elec_companies.xlsx");

            // 将文件流写入响应输出流
            org.apache.commons.io.IOUtils.copy(excelStream, response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
