package net.herdao.hdp.manpower.mpclient.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.herdao.hdp.admin.api.entity.SysDictItem;
import net.herdao.hdp.manpower.mpclient.constant.ManpowerContants;
import net.herdao.hdp.manpower.mpclient.entity.Staff;
import net.herdao.hdp.manpower.mpclient.service.StaffService;
import net.herdao.hdp.manpower.sys.service.SysDictItemService;

import java.util.Arrays;
import java.util.List;

/**
 * 批量导入校检工具类
 */
@Slf4j
public class ImportCheckUtils {

    /**
     * 员工校检
     * @param errMsg
     * @param staffCode
     * @param staffName
     * @return
     */
    public static Long checkStaff(StringBuffer errMsg, String staffCode, String staffName, StaffService staffService) {
        Long staffId=0L;
        if (null != staffCode){
            Staff staff = staffService.getOne(new QueryWrapper<Staff>().eq("staff_code", staffCode));
            if (null == staff){
                appendStringBuffer(errMsg, "系统查不到此员工工号：" + staffCode + "不存在");
            }else{
                if (!staff.getStaffName().equals(staffName)){
                    appendStringBuffer(errMsg, "员工姓名不一致：" +staffName);
                }else{
                    staffId=staff.getId();
                }
            }
        }
        return staffId;
    }


    /**
     * 校检字典
     * @param errMsg
     * @param type
     * @param lable
     */
    public static SysDictItem checkDicItem(StringBuffer errMsg, String type , String lable, SysDictItemService itemService) {
        SysDictItem dictItem = itemService.getOne(
                new QueryWrapper<SysDictItem>()
                        .eq("type", type)
                        .eq("label", lable)
                        .eq("del_flag", 0)
        );
        if (null == dictItem) {
            appendStringBuffer(errMsg, "类型不存在或已停用：" + lable);
        }

        return dictItem;
    }

    /**
     * 拼接组织异常信息
     * @param stringBuffer
     * @param errorMsg
     */
    public static void appendStringBuffer(StringBuffer stringBuffer, String errorMsg) {
        if (stringBuffer == null) {
            stringBuffer = new StringBuffer();
        }

        stringBuffer.append(stringBuffer.length() > 0 ? errorMsg + ManpowerContants.CH_SEMICOLON : errorMsg);
    }

    /**
     * 校检时间
     * @param errMsg
     * @param beginTime
     * @param endTime
     * @return
     */
    public static String checkTime(StringBuffer errMsg, String beginTime,String endTime) {
        String pattern = "";
        //校检开始时间的时间格式
        boolean checkBeginTime = false;
        List<String> formatList = Arrays.asList("yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss", "yyyy.MM.dd HH:mm:ss");
        for (String format : formatList) {
            checkBeginTime = DateUtils.isLegalDate(beginTime, format);
            if (checkBeginTime) {
                pattern = format;
                break;
            }
        }
        if (!checkBeginTime) {
            appendStringBuffer(errMsg, "请填写正确的开始时间的时间格式（yyyy/MM/dd HH:mm:ss 或者 yyyy-MM-dd HH:mm:ss 或者 yyyy.MM.dd HH:mm:ss）");
        }
        //校检结束时间的时间格式
        boolean checkEndTime = false;
        for (String format : formatList) {
            checkEndTime = DateUtils.isLegalDate(endTime, format);
            if (checkEndTime) {
                break;
            }
        }
        if (!checkEndTime) {
            appendStringBuffer(errMsg, "请填写正确的结束时间的时间格式（yyyy/MM/dd HH:mm:ss 或者 yyyy/MM/dd HH:mm:ss 或者 yyyy.MM.dd HH:mm:ss）");
        }
        //比较开始时间和结束时间
        boolean compareDateStatus = DateUtils.compareDate(beginTime, endTime, pattern);
        if (!compareDateStatus) {
            appendStringBuffer(errMsg, "结束日期必须在开始日期之后");
        }

        return pattern;
    }

    /**
     * 校检时间
     * @param errMsg
     * @param timeParam
     * @return
     */
    public static String checkSingleTime(StringBuffer errMsg, String timeParam) {
        String pattern = "";
        //校检开始时间的时间格式
        boolean checkTime = false;
        List<String> formatList = Arrays.asList("yyyy/MM/dd", "yyyy-MM-dd", "yyyy.MM.dd");
        for (String format : formatList) {
            checkTime = DateUtils.isLegalDate(timeParam, format);
            if (checkTime) {
                pattern = format;
                break;
            }
        }
        if (!checkTime) {
            appendStringBuffer(errMsg, "请填写正确的时间格式（yyyy/MM/dd 或者 yyyy-MM-dd 或者 yyyy.MM.dd）");
        }

        return pattern;
    }

    /**
     * 校检日期
     * @param errMsg
     * @param beginDate
     * @param endDate
     * @return
     */
    public static String checkDate(StringBuffer errMsg, String beginDate,String endDate) {
        String pattern = "";
        //校检开始时间的时间格式
        boolean checkBeginTime = false;
        List<String> formatList = Arrays.asList("yyyy/MM/dd", "yyyy-MM-dd", "yyyy.MM.dd");
        for (String format : formatList) {
            checkBeginTime = DateUtils.isLegalDate(beginDate, format);
            if (checkBeginTime) {
                pattern = format;
                break;
            }
        }
        if (!checkBeginTime) {
            appendStringBuffer(errMsg, "请填写正确的开始时间的时间格式（yyyy/MM/dd 或者 yyyy-MM-dd 或者 yyyy.MM.dd）");
        }
        //校检结束时间的时间格式
        boolean checkEndTime = false;
        for (String format : formatList) {
            checkEndTime = DateUtils.isLegalDate(endDate, format);
            if (checkEndTime) {
                break;
            }
        }
        if (!checkEndTime) {
            appendStringBuffer(errMsg, "请填写正确的结束时间的时间格式（yyyy/MM/dd 或者 yyyy-MM-dd 或者 yyyy.MM.dd）");
        }
        //比较开始时间和结束时间
        boolean compareDateStatus = DateUtils.compareDate(beginDate, endDate, pattern);
        if (!compareDateStatus) {
            appendStringBuffer(errMsg, "结束日期必须在开始日期之后");
        }

        return pattern;
    }
}
