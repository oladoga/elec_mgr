package com.hitelligence.elec_mgr.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hitelligence.elec_mgr.mapper.ElecCompaniesMapper;
import com.hitelligence.elec_mgr.model.ElecCompanies;
import org.springframework.stereotype.Service;

@Service
public class ElecCompaniesService extends ServiceImpl<ElecCompaniesMapper, ElecCompanies> {

    public Page<ElecCompanies> getPagedElecCompanies(int page, int pageSize) {
        return this.page(new Page<>(page, pageSize));
    }

}