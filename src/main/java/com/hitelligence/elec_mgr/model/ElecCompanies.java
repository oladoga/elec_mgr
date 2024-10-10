package com.hitelligence.elec_mgr.model;
import lombok.Data;

@Data
public class ElecCompanies {
    private Long companyId;
    private String companyNo;
    private String companyName;
    private Double lng;
    private Double lat;
}