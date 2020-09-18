package net.herdao.hdp.mpclient.controller;

import com.alibaba.excel.EasyExcel;
import lombok.AllArgsConstructor;
import net.herdao.hdp.mpclient.common.Utils.ExcelUtils;
import net.herdao.hdp.mpclient.entity.User;
import net.herdao.hdp.mpclient.listener.UserExcelListener;
import net.herdao.hdp.mpclient.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@RestController
@AllArgsConstructor
@RequestMapping("/excelTest" )
public class ExcelTestController {
    private final UserService userService;

    // easyexcel导出Excel到web
    @GetMapping("/export2Web")
    public void export2Web(HttpServletResponse response) {
        try {
            ExcelUtils.export2Web(response, "组织架构表", "组织架构表1", User.class, userService.list());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // easyexcel读取文件
    @GetMapping("/read4File")
    @ResponseBody
    public String read4File() {
        String fileName = "C:\\Users\\Administrator\\Desktop\\" + "用户表导入.xlsx";
        EasyExcel.read(fileName, User.class, new UserExcelListener(userService)).sheet().doRead();
        /* EasyExcel.read(serviceFile,User.class, new UserExcelListener(userService)).sheet().doRead();*/
        return "读取成功";
    }

    // easyexcel读取文件
    @PostMapping("/read4FileUpload")
    @ResponseBody
    public String read4FileUpload(@RequestParam(value = "file") MultipartFile file) throws IOException {
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream,User.class, new UserExcelListener(userService)).sheet().doRead();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return "读取成功";
    }

}
