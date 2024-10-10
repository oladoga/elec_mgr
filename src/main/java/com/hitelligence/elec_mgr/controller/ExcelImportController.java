package com.hitelligence.elec_mgr.controller;
import com.hitelligence.elec_mgr.model.ElecCompanies;
import com.hitelligence.elec_mgr.service.ExcelImportService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ExcelImportController {

    private final ExcelImportService excelImportService;

    public ExcelImportController(ExcelImportService excelImportService) {
        this.excelImportService = excelImportService;
    }

    @PostMapping("/upload_elec_companies")
    public Map<String, Object> uploadElecCompanies(
            @RequestPart("file") MultipartFile file,
            @RequestParam("overwrite") boolean overwrite) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<ElecCompanies> failedCompanies = excelImportService.importElecCompanies(file, overwrite);
            if (!failedCompanies.isEmpty()) {
                response.put("msg", "电力公司信息导入失败");
                response.put("failed_companies", failedCompanies);
            } else {
                response.put("msg", "电力公司信息导入成功");
                response.put("failed_companies", failedCompanies);
            }
            return response;
        } catch (Exception e) {
            response.put("msg", "数据导入失败: " + e.getMessage());
            response.put("failed_companies", null);
            return response;
        }
    }
}
