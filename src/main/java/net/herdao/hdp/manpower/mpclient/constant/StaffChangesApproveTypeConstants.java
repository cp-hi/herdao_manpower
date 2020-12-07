package net.herdao.hdp.manpower.mpclient.constant;

/**
 * 员工调动类型
 * 对应 sys_dict_item type = "YWDLX"
 *
 * @Author Liu Chang
 * @Date 2020/11/27 3:06 下午
 */
public interface StaffChangesApproveTypeConstants {
    /**
     * 调动（公司内部人事调动）
     */
    public final String TRANSFER = "3";

    /**
     * 调入调出（公司之间的调动）
     */
    public final String CALL_IN_AND_CALL_OUT = "5";

    /**
     * 晋升
     */
    public final String PROMOTE = "2";

    /**
     * 调入
     */
    String CALL_IN = "6";

    /**
     * 调出
     */
    String CALL_OUT = "7";
}
