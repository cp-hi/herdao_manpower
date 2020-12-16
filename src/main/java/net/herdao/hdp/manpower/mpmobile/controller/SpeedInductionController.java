package net.herdao.hdp.manpower.mpmobile.controller;

import io.swagger.annotations.Api;
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
@RequestMapping("/mobile/speed")
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
    @PostMapping("/bindDataAfterUploading")
    @ResponseBody
    public R bindDataAfterUploading(List<AttachFileDTO> attachFile) throws IOException {
        attachFileService.bindDataAfterUploading(attachFile);
        return R.ok();
    }

    /*    *
     * 通过id查询通用文件表数据
     * @param id id   业务表ID
     * @return R*/
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/attach/{id}")
//    @PreAuthorize("@pms.hasPermission('generator_cardinformation_view')" )
    public R getAttachById(@PathVariable("id") Long id) {
        return R.ok(attachFileService.getAttachFileById(id));
    }

    /*    *
     * 通过id查询身份证信息表
     * @param id id   业务表ID
     * @return R*/

    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/identity/{id}")
//    @PreAuthorize("@pms.hasPermission('generator_cardinformation_view')" )
    public R getIdentityById(@PathVariable("id") Long id) {
        return R.ok(cardInformationService.getIdentityById(id));
    }


    /*    *
     * 新增身份证信息表
     * @param cardInformation 身份证信息表
     * @return R*/

    @ApiOperation(value = "新增身份证信息表", notes = "新增身份证信息表")
    @SysLog("新增身份证信息表")
    @PostMapping("/saveIdentity")
//    @PreAuthorize("@pms.hasPermission('generator_cardinformation_add')" )
    public R saveIdentity(@RequestBody CardInformation cardInformation) {
        return R.ok(cardInformationService.save(cardInformation));
    }


    /*    *
     * 通过id查询工资卡信息表
     * @param id id   业务表ID
     * @return R
     *
     * */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/payCard/{id}")
    @PreAuthorize("@pms.hasPermission('generator_paycardinformation_view')")
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
    @PostMapping
    @PreAuthorize("@pms.hasPermission('generator_paycardinformation_add')")
    public R savePayCardInfo(@RequestBody PayCardInformationDTO dto) {
        return R.ok(payCardInformationService.insertPayCard(dto));
    }
}
