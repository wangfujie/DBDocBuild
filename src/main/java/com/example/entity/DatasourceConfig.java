package com.example.entity;

import lombok.Data;
import java.io.File;

/**
 * @author wangfj
 */
@Data
public class DatasourceConfig {

    /**
     * 数据源驱动包
     */
    private File driverJar;

    /**
     * 数据源驱动
     */
    private String datasourceDriver;

    /**
     * 数据源url
     */
    private String datasourceUrl;

    /**
     * 数据源用户名
     */
    private String datasourceUsername;

    /**
     * 数据源账号密码
     */
    private String datasourcePassword;

    /**
     * 数据库名称
     */
    private String dbName;

}
