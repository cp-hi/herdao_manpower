package net.herdao.hdp.manpower.mpmobile.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.entity.AttachFile;
import net.herdao.hdp.manpower.mpclient.service.AttachFileService;
import net.herdao.hdp.manpower.mpmobile.dto.AttachFileDTO;
import net.herdao.hdp.manpower.mpmobile.dto.PayCardInformationDTO;
import net.herdao.hdp.manpower.mpmobile.entity.CardInformation;
import net.herdao.hdp.manpower.mpmobile.entity.PayCardInformation;
import net.herdao.hdp.manpower.mpmobile.service.CardInformationService;
import net.herdao.hdp.manpower.mpmobile.service.PayCardInformationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @Author cp
 * @CreateDate 2020/12/16
 * @Email soul_thought@163.com
 */

@RestController
@AllArgsConstructor
@RequestMapping("mobile/speed")
@Api(tags = "极速入职-移动端")
public class SpeedInductionController {

    private final AttachFileService attachFileService;

    private final CardInformationService cardInformationService;

    private final PayCardInformationService payCardInformationService;

    /**
     * 上传后绑定数据
     *
     * @return R
     */
    @ApiOperation(value = "上传后绑定数据", notes = "上传后绑定数据")
    @PostMapping("/bindDataAfterUploading")
    @ResponseBody
    public R bindDataAfterUploading(@RequestBody  List<AttachFileDTO> attachFile) throws IOException {
        attachFileService.bindDataAfterUploading(attachFile);
        return R.ok();
    }

    /*    *
     * 通过id查询通用文件表数据
     * @param id id   业务表ID
     * @return R*/
    @ApiOperation(value = "通过业务表ID查询(例如：人才表的主键ID)", notes = "通过业务表ID查询(例如：人才表的主键ID) ")
    @GetMapping("/attach")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "业务表ID(例如：人才表的主键ID)", required = true),
            @ApiImplicitParam(name = "moduleTyp", value = "字典类型(文件所属字典类型 例如: 体检报告:MEDICAL_REPORT )", required = true)
    })
//    @PreAuthorize("@pms.hasPermission('generator_cardinformation_view')" )
    public R getAttachById( Long id, String moduleTyp) {
        return R.ok(attachFileService.getAttachFileById(id,moduleTyp));
    }




    /*    *
     * 通过id查询工资卡信息表
     * @param id id   业务表ID
     * @return R
     *
     * */
    @ApiOperation(value = "通过业务表ID查询(例如：人才表的主键ID) ", notes = "通过业务表ID查询(例如：人才表的主键ID) ")
    @GetMapping("/payCard/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "业务表ID(例如：人才表的主键ID)", required = true)
    })
//    @PreAuthorize("@pms.hasPermission('generator_paycardinformation_view')")
    public R getCardById(@PathVariable("id") Long id) {
        return R.ok(payCardInformationService.getCardById(id));
    }
    /*
     *
     * 新增工资卡信息表
     * @param payCardInformation 工资卡信息表
     * @return R*/

    @ApiOperation(value = "新增工资卡信息表", notes = "新增工资卡信息表")
    @SysLog("新增工资卡信息表")
    @PostMapping("/savePayCardInfo")
//    @PreAuthorize("@pms.hasPermission('generator_paycardinformation_add')")
    public R savePayCardInfo(@RequestBody PayCardInformationDTO dto) {
        return R.ok(payCardInformationService.insertPayCard(dto));
    }
}
