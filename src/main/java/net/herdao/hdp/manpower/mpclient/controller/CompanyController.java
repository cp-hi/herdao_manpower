
package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.dto.CompanyDetailDTO;
import net.herdao.hdp.manpower.mpclient.dto.CompanyListDTO;
import net.herdao.hdp.manpower.mpclient.dto.excelVM.company.CompanyAddVM;
import net.herdao.hdp.manpower.mpclient.dto.excelVM.company.CompanyUpdateVM;
import net.herdao.hdp.manpower.mpclient.entity.Company;
import net.herdao.hdp.manpower.mpclient.service.CompanyService;
import net.herdao.hdp.manpower.mpclient.service.HdpService;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.mpclient.vo.CompanyVO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


/**
 * 注册公司
 *
 * @author liang
 * @date 2020-09-15 17:10:12
 */
@RestController
@AllArgsConstructor
@RequestMapping("/company" )
@Api(value = "company", tags = "注册公司管理")
public class CompanyController extends HdpBaseController{

    private final CompanyService companyService;

    @Override
    public HdpService getHdpService() {
        return this.companyService;
    }

    @Override
    public Class getImportAddCls() {
        return CompanyAddVM.class;
    }

    @Override
    public Class getImportUpdateCls() {
        return CompanyUpdateVM.class;
    }
    /**
     * 分页查询
     * @param page 分页对象
     * @param company 注册公司
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
//    @PreAuthorize("@pms.hasPermission('mpclient_mpcompany_view')" )
    public R getCompanyPage(Page page, CompanyListDTO company, String searchText) {
        return R.ok(companyService.companyPage(page, company, searchText));
    }
    @ApiOperation(value = "导出公司", notes = "导出公司")
    @GetMapping("/export" )
    @SneakyThrows
    public void export(HttpServletResponse response, CompanyListDTO company, String searchText) {
        Page page = new Page();
        page.setSize(-1);
        IPage iPage = companyService.companyPage(page, company, searchText);
        ExcelUtils.export2Web(response, "公司", "公司", CompanyListDTO.class, iPage.getRecords());
    }

    /**
     * 通过id查询注册公司
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
//    @PreAuthorize("@pms.hasPermission('mpclient_mpcompany_view')" )
    public R<CompanyDetailDTO> getById(@PathVariable("id" ) Long id) {
        return R.ok(companyService.getCompanyById(id));
    }

    /**
     * 新增注册公司
     * @param companyForm 注册公司
     * @return R
     */
    @ApiOperation(value = "新增注册公司", notes = "新增注册公司")
    @SysLog("新增注册公司" )
    @PostMapping
//    @PreAuthorize("@pms.hasPermission('mpclient_mpcompany_add')" )
    public R save(@RequestBody CompanyDetailDTO companyForm) {
        companyService.companySave(companyForm);
        return R.ok(companyForm);
    }

    /**
     * 修改注册公司
     * @param companyForm 注册公司
     * @return R
     */
    @ApiOperation(value = "修改注册公司", notes = "修改注册公司")
    @SysLog("修改注册公司" )
    @PutMapping
//    @PreAuthorize("@pms.hasPermission('mpclient_mpcompany_edit')" )
    public R<Boolean> updateById(@RequestBody CompanyDetailDTO companyForm) {
        return R.ok(companyService.companyUpdate(companyForm));
    }

    /**
     * 通过id删除注册公司
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除注册公司", notes = "通过id删除注册公司")
    @SysLog("通过id删除注册公司" )
    @DeleteMapping("/{id}" )
//    @PreAuthorize("@pms.hasPermission('mpclient_mpcompany_del')" )
    public R<Boolean> removeById(@PathVariable Long id) {
        return R.ok(companyService.removeById(id));
    }


    /**
     * 公司列表
     * @return
     */
    @ApiOperation(value = "公司列表", notes = "公司列表")
    @GetMapping("/getCompanyList" )
    public R getCompanyList() {
        List<Company> list = companyService.list();
        return R.ok(list);
    }


    /**
     * 公司列表-下拉-前端调用
     * @return
     */
    @ApiOperation(value = "公司列表-下拉-前端调用", notes = "公司列表-下拉-前端调用")
    @GetMapping("/getCompanyListVO" )
    public R<List<CompanyVO>> getCompanyListVO() {
        List<Company> list = companyService.list();
        List<CompanyVO> resultList=new ArrayList<>();
        for (Company company : list) {
            CompanyVO companyVO=new CompanyVO();
            companyVO.setLabel(company.getCompanyName());
            companyVO.setId(company.getId());
            resultList.add(companyVO);
        }

        return R.ok(resultList);
    }


}
