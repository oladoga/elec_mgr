package com.hitelligence.elec_mgr.model;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor  // 无参构造函数
@AllArgsConstructor // 全参构造函数
@TableName("jb_company")  // 将表名改为数据库中的实际表名
public class ElecCompanies {
    @TableId
    private Long companyId;
    private String companyNo;
    private String companyName;
    private Double lng;
    private Double lat;
}