package com.hitelligence.elec_mgr.service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hitelligence.elec_mgr.mapper.ElecCompaniesMapper;
import com.hitelligence.elec_mgr.model.ElecCompanies;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
@Service
public class ElecCompaniesService {

    private final ElecCompaniesMapper elecCompaniesMapper;

    public ElecCompaniesService(ElecCompaniesMapper elecCompaniesMapper) {
        this.elecCompaniesMapper = elecCompaniesMapper;
    }

    public PageInfo<ElecCompanies> getAllElecCompanies(int page, int pageSize) {
        // 调用 PageHelper 的 startPage 方法
        PageHelper.startPage(page, pageSize);
        List<ElecCompanies> elecCompanies = elecCompaniesMapper.getAllElecCompanies();
        // 使用 PageInfo 包装结果并返回
        return new PageInfo<>(elecCompanies);
    }

    public void createElecCompany(ElecCompanies company) {
        elecCompaniesMapper.insertElecCompany(company);
    }

    public ElecCompanies getElecCompanyById(Long companyId) {
        return elecCompaniesMapper.selectElecCompanyById(companyId);
    }

    public void updateElecCompany(Long companyId, ElecCompanies company) {
        company.setCompanyId(companyId);
        elecCompaniesMapper.updateElecCompany(company);
    }
    public void deleteElecCompany(Long companyId) {
        elecCompaniesMapper.deleteElecCompany(companyId);
    }
}