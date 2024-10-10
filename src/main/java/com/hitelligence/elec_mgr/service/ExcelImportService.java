package com.hitelligence.elec_mgr.service;

import com.hitelligence.elec_mgr.mapper.ElecCompaniesMapper;
import com.hitelligence.elec_mgr.model.ElecCompanies;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelImportService {

    private final ElecCompaniesMapper elecCompaniesMapper;

    public ExcelImportService(ElecCompaniesMapper elecCompaniesMapper) {
        this.elecCompaniesMapper = elecCompaniesMapper;
    }

    public List<ElecCompanies> importElecCompanies(MultipartFile file, boolean overwrite) throws Exception {
        List<ElecCompanies> failedCompanies = new ArrayList<>();

        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {  // 从第二行开始，跳过表头
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }

                try {
                    Long companyId = (long) row.getCell(0).getNumericCellValue();
                    String companyNo = row.getCell(1).getStringCellValue();
                    String companyName = row.getCell(2).getStringCellValue();
                    Double lng = row.getCell(3) != null ? row.getCell(3).getNumericCellValue() : null;
                    Double lat = row.getCell(4) != null ? row.getCell(4).getNumericCellValue() : null;

                    ElecCompanies company = new ElecCompanies();
                    company.setCompanyId(companyId);
                    company.setCompanyNo(companyNo);
                    company.setCompanyName(companyName);
                    company.setLng(lng);
                    company.setLat(lat);

                    if (overwrite) {
                        ElecCompanies existingCompany = elecCompaniesMapper.selectElecCompanyById(companyId);
                        if (existingCompany != null) {
                            elecCompaniesMapper.deleteElecCompany(companyId);  // 删除原有数据
                        }
                    }

                    elecCompaniesMapper.insertElecCompany(company);  // 插入新数据
                } catch (Exception e) {
                    // 如果有异常，将失败的数据添加到失败列表中
                    failedCompanies.add(new ElecCompanies(
                            (long) row.getCell(0).getNumericCellValue(),
                            row.getCell(1).getStringCellValue(),
                            row.getCell(2).getStringCellValue(),
                            row.getCell(3) != null ? row.getCell(3).getNumericCellValue() : null,
                            row.getCell(4) != null ? row.getCell(4).getNumericCellValue() : null
                    ));
                }
            }
        }

        return failedCompanies;
    }
}
