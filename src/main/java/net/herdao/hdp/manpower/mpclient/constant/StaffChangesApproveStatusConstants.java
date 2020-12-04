package net.herdao.hdp.manpower.mpclient.constant;

/**
 * @Author Liu Chang
 * @Date 2020/11/26 4:40 下午
 */
public interface StaffChangesApproveStatusConstants {
    /**
     *  填报中
     */
    public final String FILLING_IN = "1";

    /**
     *  审核中
     */
    public final String APPROVING = "2";

    /**
     * 已审批
     */
    public final String APPROVED = "3";

    /**
     * 审批通过 - 执行结束
     */
    public final String EXECUTED = "5";

    /**
     * 审批否决 - 执行结束
     */
    public final String DENIED = "6";
}
