package com.hitelligence.elec_mgr.mapper;

import com.hitelligence.elec_mgr.model.ElecCompanies;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ElecCompaniesMapper {
    List<ElecCompanies> getAllElecCompanies();
    void insertElecCompany(ElecCompanies company);
    ElecCompanies selectElecCompanyById(Long companyId);
    void updateElecCompany(ElecCompanies company);
    void deleteElecCompany(Long companyId);
}
