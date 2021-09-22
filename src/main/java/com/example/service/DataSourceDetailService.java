package com.example.service;

import com.lowagie.text.DocumentException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public interface DataSourceDetailService {

    /**
     * 根据数据库名称和表名称获取表的详细信息
     * @param dbName
     * @param tableName
     * @return
     */
    List<Map<String, Object>> getDataSourceDetail(String dbName, String tableName);

    /**
     * 根据数据库名称获取数据库中表的名称和注释
     * @param dbName
     * @return
     */
    List<Map<String, Object>> getAllDataSourceName(String dbName);

    /**
     * 将数据写出到指定的word文档中
     * @param listAll
     * @param dbName
     * @throws FileNotFoundException
     * @throws DocumentException
     */
    void toWord(List<Map<String, Object>> listAll, String dbName) throws FileNotFoundException, DocumentException;
}
