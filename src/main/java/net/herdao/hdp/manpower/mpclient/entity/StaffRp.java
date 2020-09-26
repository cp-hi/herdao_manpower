/*
 *    Copyright (c) 2018-2025, hdp All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: hdp
 */

package net.herdao.hdp.manpower.mpclient.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseModel;

import java.time.LocalDateTime;

/**
 * 员工奖惩
 *
 * @author andy
 * @date 2020-09-25 16:26:20
 */
@Data
public class StaffRp extends Model<StaffRp> {
private static final long serialVersionUID = 1L;

    /**
     * 奖惩类别 通报表扬；年度优秀员工奖；特殊贡献奖。下拉框选择
     */
    @ExcelProperty(value = "奖惩类别", index = 0)
   private String type;

    /**
     * 奖惩内容
     */
    @ExcelProperty(value = "奖惩内容", index = 1)
    private String content;

    /**
     * 奖惩金额
     */
    @ExcelProperty(value = "奖惩金额", index = 2)
    private String amount;

    /**
     * 奖惩原因
     */
    @ExcelProperty(value = "奖惩原因", index = 3)
    private String reason;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注", index = 4)
    private String remarks;

    /**
     * 新建用户
     */
    @ExcelProperty(value = "新建用户", index = 5)
    private String creatorCode;


    /**
     * 最后修改人
     */
    @ExcelProperty(value = "最后修改人", index = 6)
    private String modifierCode;




}
