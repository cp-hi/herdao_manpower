package net.herdao.hdp.manpower.mpclient.controller;

import com.alibaba.excel.EasyExcel;
import lombok.AllArgsConstructor;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.mpclient.entity.User;
import net.herdao.hdp.manpower.mpclient.listener.UserExcelListener;
import net.herdao.hdp.manpower.mpclient.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;



/**
 *
 * excel 测试类
 * @author andy
 * @date 2020-09-18 19:46:22
 */
@RestController
@AllArgsConstructor
@RequestMapping("/excelTest" )
public class ExcelTestController {
    private final UserService userService;


    /**
     * easyexcel导出Excel到web
     * @param  response
     * @return R
     */
    @GetMapping("/export2Web")
    public R export2Web(HttpServletResponse response) {
        try {
            ExcelUtils.export2Web(response, "组织架构表", "组织架构表1", User.class, userService.list());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  R.ok("导出成功");
    }

     /**
     *  easyexcel读取文件
     * @return R
     */
    @GetMapping("/read4File")
    @ResponseBody
    public R read4File() {
        String fileName = "C:\\Users\\Administrator\\Desktop\\" + "用户表导入.xlsx";
        EasyExcel.read(fileName, User.class, new UserExcelListener(userService)).sheet().doRead();
        return  R.ok(" easyexcel成读取文件");
    }

    /**
     * easyexcel读取上传文件
     * @return R
     */
    @PostMapping("/read4FileUpload")
    @ResponseBody
    public R read4FileUpload(@RequestParam(value = "file") MultipartFile file) throws IOException {
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream,User.class, new UserExcelListener(userService)).sheet().doRead();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return R.ok(" easyexcel读取上传文件成功");
    }

}
