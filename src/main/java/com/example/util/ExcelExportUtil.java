package com.example.util;

import cn.hutool.core.io.IoUtil;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author wangfj
 */
public class ExcelExportUtil {

    /**
     * 	导出Word
     */
    public static <E> void exportWord(HttpServletResponse response, File file) {

        try {
            // 将Excel写入到response的输出流中，并关闭输出流
            IoUtil.copy(new FileInputStream(file), response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
