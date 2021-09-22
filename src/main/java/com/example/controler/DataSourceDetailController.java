package com.example.controler;

import com.example.service.DataSourceDetailService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 生成数据库表设计文档
 **/
@RestController
@RequestMapping("/db")
public class DataSourceDetailController {

    @Resource
    private DataSourceDetailService dataSourceDetailService;

    /**
     * 生成数据库表设计文档
     * @param dbName 数据库名称
     * @return
     */
    @RequestMapping("/getDbDoc")
    public String getDbDetail(String dbName){
        try {
            List<Map<String,Object>> list = this.dataSourceDetailService.getAllDataSourceName(dbName);
            this.dataSourceDetailService.toWord(list, dbName);
        } catch (Exception e) {
            e.printStackTrace();
            return "生成数据库表设计文档失败";
        }
        return "生成数据库表设计文档成功";
    }
}
