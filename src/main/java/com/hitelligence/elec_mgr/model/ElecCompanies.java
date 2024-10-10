package com.hitelligence.elec_mgr.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor  // 无参构造函数
@AllArgsConstructor // 全参构造函数
public class ElecCompanies {
    private Long companyId;
    private String companyNo;
    private String companyName;
    private Double lng;
    private Double lat;
}