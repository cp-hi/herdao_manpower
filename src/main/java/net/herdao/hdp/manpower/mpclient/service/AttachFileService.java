package net.herdao.hdp.manpower.mpclient.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;

import net.herdao.hdp.manpower.mpclient.dto.attachFile.AttachFileAddDTO;
import net.herdao.hdp.manpower.mpclient.dto.attachFile.AttachFileSituationDTO;
import net.herdao.hdp.manpower.mpclient.entity.AttachFile;
import net.herdao.hdp.manpower.mpmobile.dto.AttachFileDTO;
import net.herdao.hdp.manpower.mpmobile.dto.AttachFileInfoDTO;

/**
 * 通用附件表
 *
 * @author Andy
 * @date 2020-12-15 10:55:40
 */
public interface AttachFileService extends IService<AttachFile> {

    /**
     * 上传后绑定数据
     * @param attachFile 通用附件表
     * @return R
     */
    void bindDataAfterUploading(List<AttachFileDTO> attachFile);


    /**
     * 通过id查询通用文件表数据
     * @param id id   业务表ID (例如：人才表的主键ID)
     * @param  moduleTyp   字典类型(文件所属字典类型 例如: 体检报告:MEDICAL_REPORT)
     * @return R
     */
    List<AttachFileInfoDTO> getAttachFileById(Long id,String moduleTyp);

    /**
     * 获取-个人简历-简历附件
     * @return AttachFileSituationDTO
     */
    List<AttachFileSituationDTO> fetchResumeAttachFileInfo();

    /**
     * 获取-入职登记详情-入职附件
     * @return List<AttachFileSituationDTO>
     */
    List<AttachFileSituationDTO> fetchEntryAttachFileInfo();

    /**
     * 上传后删除数据
     *
     * @param attachFile 通用附件表
     * @return R
     */
    void delDataAfterUploading(AttachFileDTO attachFile);


    /**
     * 极速入职-入职完成情况
     *
     * @param id 业务表ID (例如：人才表的主键ID)
     * @return R
     */
    Map<String,Boolean> getFinishCase(String id);

    /**
     * 	根据业务表单ID查询附件列表
     * @param bizId
     * @return
     */
	List<AttachFile> getAttachFileByBizId(Long bizId);

	/**
	 * 保存附件
	 * @param attachFile
	 * @return
	 */
	Boolean saveAttachFile(AttachFileAddDTO attachFile);

	/**
	 * 	删除文件
	 * @param fileId
	 * @return
	 */
	String deleteByFileId(String fileId);

}
