package com.hitelligence.elec_mgr.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hitelligence.elec_mgr.model.ElecCompanies;
import com.hitelligence.elec_mgr.service.ElecCompaniesService;
import org.springframework.web.bind.annotation.*;


@RestController
public class ElecCompaniesController {

    private final ElecCompaniesService elecCompaniesService;

    public ElecCompaniesController(ElecCompaniesService elecCompaniesService) {
        this.elecCompaniesService = elecCompaniesService;
    }

    /**
     * 获取电力公司列表
     *
     * @param page 页码，默认为第1页
     * @param pageSize 每页大小，默认为10
     * @return 返回分页的电力公司列表
     */
    @GetMapping("/elec_companies")
    public Page<ElecCompanies> getElecCompanies(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return elecCompaniesService.getPagedElecCompanies(page, pageSize);
    }

    /**
     * 新增电力公司信息
     * 该方法用于通过HTTP POST请求创建一个新的电力公司信息对象
     * 它接收一个包含电力公司信息的JSON对象，然后调用服务层方法将该信息保存到数据库中
     *
     * @param company 电力公司信息对象，包含电力公司的详细信息，如名称、地址等
     *                该对象由前端发送的JSON请求体解析而来
     */
    @PostMapping("/elec_companies")
    public String createElecCompany(@RequestBody ElecCompanies company) {
        elecCompaniesService.save(company);
        return "电力公司信息新增成功！";
    }

    @GetMapping("/elec_companies/{companyId}")
    public ElecCompanies getElecCompany(@PathVariable Long companyId) {
        return elecCompaniesService.getById(companyId);
    }

    @PutMapping("/elec_companies/{companyId}")
    public String updateElecCompany(@PathVariable Long companyId, @RequestBody ElecCompanies company) {
        company.setCompanyId(companyId);
        elecCompaniesService.updateById(company);
        return "电力公司信息更新成功";
    }

    @DeleteMapping("/elec_companies/{companyId}")
    public String deleteElecCompany(@PathVariable Long companyId) {
        elecCompaniesService.removeById(companyId);
        return "电力公司信息删除成功";
    }
}