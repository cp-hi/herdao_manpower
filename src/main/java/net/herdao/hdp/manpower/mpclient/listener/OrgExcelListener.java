package net.herdao.hdp.manpower.mpclient.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import net.herdao.hdp.admin.api.entity.SysDictItem;
import net.herdao.hdp.manpower.mpclient.entity.ExcelOperateRecord;
import net.herdao.hdp.manpower.mpclient.entity.Organization;
import net.herdao.hdp.manpower.mpclient.entity.Post;
import net.herdao.hdp.manpower.mpclient.entity.User;
import net.herdao.hdp.manpower.mpclient.service.ExcelOperateRecordService;
import net.herdao.hdp.manpower.mpclient.service.OrganizationService;
import net.herdao.hdp.manpower.mpclient.service.PostService;
import net.herdao.hdp.manpower.mpclient.service.UserService;
import net.herdao.hdp.manpower.sys.service.SysDictItemService;
import org.springframework.util.CollectionUtils;

import java.util.*;


/**
 * 组织excel导入监听处理
 * @author Andy
 * @date 2020-09-17 11:03:56
 */
@Slf4j
public class OrgExcelListener extends AnalysisEventListener<Organization> {

    private OrganizationService organizationService;

    private SysDictItemService sysDictItemService;

    private UserService userService;

    private PostService postService;

    private ExcelOperateRecordService excelOperateRecordService;

    public OrgExcelListener(OrganizationService organizationService, SysDictItemService sysDictItemService, UserService userService, PostService postService, ExcelOperateRecordService excelOperateRecordService) {
        this.organizationService = organizationService;
        this.sysDictItemService = sysDictItemService;
        this.userService = userService;
        this.postService = postService;
        this.excelOperateRecordService = excelOperateRecordService;
    }

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 500;
    List<Organization> list = new ArrayList<Organization>();

    @Override
    public void invoke(Organization data, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);
        if (list.size() >= BATCH_COUNT) {
            saveData();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", list.size());
        if (!CollectionUtils.isEmpty(list)) {
            //保存组织
            saveCurOrgCode();
            //organizationService.saveBatch(list);
        }
        log.info("存储数据库成功！");
    }

    /**
     * 保存组织
     */
    private void saveCurOrgCode() {
        boolean importFlag=true;
        List<Map<String,Object>> importErrorList=new ArrayList<>();
        //导入前先校检
        importFlag = checkOrg(importFlag, importErrorList);

        //如果校检通过 则导入数据
        if (importFlag){
            for (Organization org : list) {
                    if (null != org.getParentIdStr() && !org.getParentIdStr().isEmpty()){
                        Map<String,Object> parentParams =new HashMap<>();
                        parentParams.put("orgCode",org.getParentIdStr());
                        Organization parentOrg = getOrg(parentParams);
                        if (parentOrg!=null){
                            org.setParentId(parentOrg.getId());
                            org.setOrgTreeLevel(parentOrg.getOrgTreeLevel()+1);
                        }

                        //生成组织编码 orgCode
                        createOrgCode(org, parentOrg);
                    }

                    organizationService.save(org);
                }
            }else{ //记录excel导入错误信息
                for (Map<String, Object> map : importErrorList) {
                    Set<Map.Entry<String, Object>> entries = map.entrySet();
                    for (Map.Entry<String, Object> entry : entries) {
                        ExcelOperateRecord record=new ExcelOperateRecord();
                        record.setFiled(entry.getKey());
                        record.setError(entry.getValue().toString());
                        excelOperateRecordService.save(record);
                    }
                }
            }
     }

    /**
     * 生成组织编码 orgCode
     */
    private void createOrgCode(Organization org, Organization parentOrg) {
        Map<String,Object> childrenParams =new HashMap<>();
        childrenParams.put("parentId",parentOrg.getId());
        List<Organization> childOrgList = getOrgList(childrenParams);
        //挂靠父组织 父组织下有多个子组织 拿子组织中最大的组织编码
        if (childOrgList !=null && !childOrgList.isEmpty()){
            String orgCodeTemp ="";
            Long maxOrgCode=null;
            for (Organization organization : childOrgList) {
                if (null == maxOrgCode){
                    maxOrgCode = Long.parseLong(organization.getOrgCode());
                    orgCodeTemp = organization.getOrgCode();
                }
                if (Long.parseLong(organization.getOrgCode())>maxOrgCode){
                    maxOrgCode = Long.parseLong(organization.getOrgCode());
                    orgCodeTemp = organization.getOrgCode();
                }

                org.setOrgCode(orgCodeTemp);
            }

            //组装组织编码
            if (orgCodeTemp !=null && !orgCodeTemp.isEmpty()){
                int orgLength = orgCodeTemp.length();
                Long temp= Long.parseLong(orgCodeTemp)+1;
                String newOrgCode= String.format("%0"+orgLength+"d", temp);
                org.setOrgCode(newOrgCode);
            }
        }else { // 挂靠父组织 父组织下没有子组织 则父组织是最大的组织编码
            String newOrgCode=org.getParentIdStr()+"001";
            org.setOrgCode(newOrgCode);
        }
    }

    /**
     * 导入前先校检组织
     */
    private boolean checkOrg(boolean importFlag, List<Map<String, Object>> importErrorList) {
        //导入前先校检，校检合格后才导入
        for (Organization org : list) {
           //织名称、上级组织编码必填
           if (org.getOrgName()==null && org.getOrgCode()==null){
               Map<String,Object> errorMap=new HashMap<>();
               errorMap.put("必填","组织名称、上级组织编码必填");
               importErrorList.add(errorMap);
               importFlag =false;
           }

           //组织名称唯一性校验
           Map<String,Object> paramMap =new HashMap<>();
           paramMap.put("orgName",org.getOrgName());
           paramMap.put("isStop",1);
           Organization entity = getOrg(paramMap);
           if (entity!=null){
               Map<String,Object> errorMap=new HashMap<>();
               errorMap.put("orgName",org.getOrgName()+"组织名称已存在");
               importErrorList.add(errorMap);
               importFlag =false;
           }

           //上级组织编码有效性校验
           Map<String,Object> paramCodeMap =new HashMap<>();
           paramCodeMap.put("id",org.getParentId());
           paramMap.put("isStop",1);
           Organization parentEntity = getOrg(paramMap);
           if (parentEntity==null){
               Map<String,Object> errorMap=new HashMap<>();
               errorMap.put("parentId",org.getOrgName()+"上级组织编码不存在或已停用");
               importErrorList.add(errorMap);
               importFlag =false;
           }

           //组织类型校验
           boolean orgTypeFlag=false;
           if (org.getOrgType()!=null){
               List<SysDictItem> zzlxList = sysDictItemService.list(Wrappers.<SysDictItem>query().lambda().eq(SysDictItem::getType, "ZZLX"));
               if (zzlxList !=null && !zzlxList.isEmpty()){
                   for (SysDictItem item : zzlxList) {
                      if (item.getLabel().equals(org.getOrgType())) {
                          org.setOrgType(item.getId().toString());
                          orgTypeFlag = true;
                          break;
                      }
                   }
               }
           }
           if (orgTypeFlag==false){
               Map<String,Object> errorMap=new HashMap<>();
               errorMap.put("orgType",org.getOrgName()+"组织类型不存在");
               importErrorList.add(errorMap);
               importFlag =false;
           }

            //组织负责人校检
            if (org.getOrgChargeWorkNo()!=null){
                User user = userService.getById(org.getOrgChargeWorkNo());
                if (null == user) {
                    importFlag =false;
                    Map<String,Object> errorMap=new HashMap<>();
                    errorMap.put("orgChargeWorkNo",org.getOrgName()+"组织负责人不存在或已离职");
                    importErrorList.add(errorMap);
                }
            }

            //岗位负责人校检
            if (org.getOrgChargeWorkNo()!=null){
                Post post = postService.getById(org.getChargeOrg());
                if (null == post) {
                    importFlag =false;
                    Map<String,Object> errorMap=new HashMap<>();
                    errorMap.put("chargeOrg",org.getOrgName()+"负责岗位不存在或已停用");
                }
            }

            //若Excel中填写了福利类型，需校验岗位是否在系统已存在且福利类型态，否则不允许导入
       }
        return importFlag;
    }

    /**
     * 获取单个组织
     */
    private Organization getOrg(Map<String,Object> params){
        QueryWrapper<Organization> wrapper = Wrappers.query();
        if ( null!=params && !params.isEmpty()){
            if (params.get("id")!=null){
                wrapper.eq("id",params.get("id"));
            }

            if (params.get("orgCode")!=null){
                wrapper.eq("org_code",params.get("orgCode"));
            }

            if (params.get("orgName")!=null){
                wrapper.eq("org_name",params.get("orgName"));
            }
        }

        Organization org = organizationService.getOne(wrapper);
        return org;
    }

    /**
     * 获取组织集合
     */
    private List<Organization> getOrgList(Map<String,Object> params){
        QueryWrapper<Organization> wrapper = Wrappers.query();
        if ( null!=params && !params.isEmpty()){
            if (params.get("parentId")!=null){
                wrapper.eq("parent_id",params.get("parentId"));
            }
        }

        List<Organization> list = organizationService.list(wrapper);
        return list;
    }

}
