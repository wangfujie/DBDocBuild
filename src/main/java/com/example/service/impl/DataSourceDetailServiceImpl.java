package com.example.service.impl;

import com.example.dao.DataSourceMapper;
import com.example.service.DataSourceDetailService;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.rtf.RtfWriter2;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * 实现类
 **/
@Service
public class DataSourceDetailServiceImpl implements DataSourceDetailService {

    @Resource
    private DataSourceMapper dataSourceMapper;

    @Override
    public List<Map<String, Object>> getDataSourceDetail(String dbName, String tableName) {
        return dataSourceMapper.getDataDetail(dbName, tableName);
    }

    @Override
    public List<Map<String, Object>> getAllDataSourceName(String dbName) {
        return dataSourceMapper.getAllDataSourceName(dbName);
    }

    @Override
    public void toWord(OutputStream outputStream, List<Map<String, Object>> listAll, String dbName) throws FileNotFoundException, DocumentException {
        // 创建word文档,并设置纸张的大小
        Document document = new Document(PageSize.A4);
        // 创建word文档
        RtfWriter2.getInstance(document, outputStream);
        document.open();// 设置文档标题
        Paragraph ph = new Paragraph();
        Font f = new Font();
        Paragraph p = new Paragraph("数据库表设计文档", new Font(Font.NORMAL, 24, Font.BOLDITALIC, new Color(0, 0, 0)));
        p.setAlignment(1);
        document.add(p);
        ph.setFont(f);/* * 创建表格 通过查询出来的表遍历 */
        for (int i = 0; i < listAll.size(); i++) {
            // 表名
            String table_name = (String) listAll.get(i).get("TABLE_NAME");
            // 表说明
            String table_comment = (String) listAll.get(i).get("TABLE_COMMENT");
            //获取某张表的所有字段说明
            List<Map<String, Object>> list = this.getDataSourceDetail(dbName, table_name);
            //构建表说明
            String all = "" + (i + 1) + " 表名：" + table_name + " " + table_comment + "";
            //创建有6列的表格
            Table table = new Table(6);
            document.add(new Paragraph(""));
            table.setBorderWidth(1);
            // table.setBorderColor(Color.BLACK);
            table.setPadding(0);
            table.setSpacing(0);
            /*
             * 添加表头的元素，并设置表头背景的颜色
             */
            Color chade = new Color(176, 196, 222);
            Cell cell = new Cell("序号");// 单元格
            cell.setBackgroundColor(chade);
            cell.setHeader(true);
            // cell.setColspan(3);//设置表格为三列
            // cell.setRowspan(3);//设置表格为三行
            table.addCell(cell);
            cell = new Cell("字段名");// 单元格
            cell.setBackgroundColor(chade);
            table.addCell(cell);
            cell = new Cell("类型");// 单元格
            cell.setBackgroundColor(chade);
            table.addCell(cell);
            cell = new Cell("是否为空");// 单元格
            cell.setBackgroundColor(chade);
            table.addCell(cell);
            cell = new Cell("主键");// 单元格
            cell.setBackgroundColor(chade);
            table.addCell(cell);
            cell = new Cell("字段说明");// 单元格
            cell.setBackgroundColor(chade);
            table.addCell(cell);
            table.endHeaders();// 表头结束
            // 表格的主体，
            for (int k = 0; k < list.size(); k++) {
                //获取某表每个字段的详细说明
                String Field = (String) list.get(k).get("Field");
                String Type = (String) list.get(k).get("Type");
                String Null = (String) list.get(k).get("Null");
                String Key = (String) list.get(k).get("Key");
                String Comment = (String) list.get(k).get("Comment");
                table.addCell((k + 1) + "");
                table.addCell(Field);
                table.addCell(Type);
                table.addCell(Null);
                table.addCell(Key);
                table.addCell(Comment);
            }
            Paragraph pheae = new Paragraph(all);
            //写入表说明
            document.add(pheae);
            //生成表格
            document.add(table);
        }
        document.close();
    }
}
