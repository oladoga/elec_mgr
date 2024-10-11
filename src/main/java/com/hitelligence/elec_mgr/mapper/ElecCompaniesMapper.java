package com.hitelligence.elec_mgr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hitelligence.elec_mgr.model.ElecCompanies;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ElecCompaniesMapper extends BaseMapper<ElecCompanies> {
    // 继承 BaseMapper 后，自动拥有常用的 CRUD 操作方法
    // 可以在这里添加自定义的 SQL 方法
}