package com.nep.util;

import com.nep.entity.AqiFeedback;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.util.List;
import java.util.logging.Logger;

public class ExcelExportUtil {
    private static final Logger logger = LogUtil.getLogger(ExcelExportUtil.class);

    public static void exportAqiFeedbackToExcel(List<AqiFeedback> dataList, String filePath) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("AQI反馈信息");

        // 创建表头
        Row header = sheet.createRow(0);
        String[] titles = {"编号", "姓名", "省", "市", "地址", "反馈信息", "预估等级", "状态", "反馈日期"};
        for (int i = 0; i < titles.length; i++) {
            Cell cell = header.createCell(i);
            cell.setCellValue(titles[i]);
        }

        // 填充数据
        int rowIndex = 1;
        for (AqiFeedback af : dataList) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(af.getAfId());
            row.createCell(1).setCellValue(af.getAfName());
            row.createCell(2).setCellValue(af.getProviceName());
            row.createCell(3).setCellValue(af.getCityName());
            row.createCell(4).setCellValue(af.getAddress());
            row.createCell(5).setCellValue(af.getInfomation());
            row.createCell(6).setCellValue(af.getEstimateGrade());
            row.createCell(7).setCellValue(af.getState());
            row.createCell(8).setCellValue(af.getDate());
        }

        try (FileOutputStream out = new FileOutputStream(filePath)) {
            workbook.write(out);
            logger.info(String.format("AQI数据excel导出成功，路径：%s", filePath));
        } catch (Exception e) {
            logger.severe(String.format("AQI数据excel导出失败 - 错误：%s", e.getMessage()));
        }
    }
}
