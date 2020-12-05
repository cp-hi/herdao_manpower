package net.herdao.hdp.manpower.mpclient.constant;

import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

/**
 * 合同期限类型
 *  * 对应 sys_dict_item type = "HTQXLX"
 *
 * @Author Liu Chang
 * @Date 2020/12/5 4:48 下午
 */
public interface ContractDurationTypeConstants {

    /**
     *  固定期限
     */
    public final String FIXED_TERM = "1";

    /**
     * 无固定期限
     */
    public final String INDEFINITE_PERIOD = "2";

    /**
     * 劳务协议
     */
    public final String  LABOR_AGREEMENT = "3";

    /**
     * 以完成一定的工作为期限
     */
    public final String COMPLETION_OF_CERTAIN_TASKS = "4";

    /**
     * 离退休人员返聘合同
     */
    public final String REEMPLOYMENT_CONTRACT_FOR_RETIRED_PERSONNEL = "5";
}
