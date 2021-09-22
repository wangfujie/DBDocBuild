package com.example.controler;

import com.example.entity.DatasourceConfig;
import com.example.service.DataSourceDetailService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.activation.MimetypesFileTypeMap;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
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
     * @param datasourceConfig 参数
     * @return
     */
    @RequestMapping("/getDbDoc")
    public void getDbDetail(DatasourceConfig datasourceConfig) throws Exception {
        datasourceConfig.setDbName("esbmanage");
        String filename = "数据库文档.doc";
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletResponse response = requestAttributes.getResponse();
        // 设置信息给客户端不解析
        String fileType = new MimetypesFileTypeMap().getContentType(filename);
        // 设置contenttype，即告诉客户端所发送的数据属于什么类型
        assert response != null;
        response.setHeader("content-Type", fileType);
        // 设置编码
        String fileName = new String(filename.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        // 设置扩展头，当Content-Type 的类型为要下载的类型时 , 这个信息头会告诉浏览器这个文件的名字和类型。
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        try {
            List<Map<String,Object>> list = this.dataSourceDetailService.getAllDataSourceName(datasourceConfig.getDbName());
            this.dataSourceDetailService.toWord(response.getOutputStream(), list, datasourceConfig.getDbName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
